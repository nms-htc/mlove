/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.BaseEntity_;
import com.nms.mlove.entity.User;
import com.nms.mlove.entity.User.Group;
import com.nms.mlove.entity.User_;
import com.nms.mlove.service.UserService;
import com.nms.mlove.util.Validator;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author MinhDT
 */
@Stateless
public class UserServiceBean extends AbstractService<User> implements UserService{

    private static final long serialVersionUID = -9209050114449526152L;
    
    private static final Logger LOGGER = Logger.getLogger(UserServiceBean.class.getName());
    
    public UserServiceBean() {
        super(User.class);
    }
    
    @Override
    public User findByUP(String username, String password) {
        try {
            password = hashSHA256(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EJBException("can-not-hash(sha256)-password", e);
        }
        TypedQuery<User> q = em.createNamedQuery("User.findByUP", User.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        return q.getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> q = em.createNamedQuery("User.findByU", User.class);
        q.setParameter("username", username);
        return q.getSingleResult();
    }

    @Override
    protected void onBeforePersist(User entity) {
        try {
            entity.setPassword(hashSHA256(entity.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EJBException("can-not-hash(sha256)-password", e);
        }
    }

    @Override
    protected Predicate buildCondition(Map.Entry<String, Object> entry, Root<User> root, CriteriaBuilder cb) {
        Predicate predicate = null;
        switch (entry.getKey()) {
            case "username":
                predicate = cb.like(cb.upper(root.get(User_.username)), "%" + entry.getValue().toString().toUpperCase() + "%");
                break;
            case "fullname":
                predicate = cb.like(cb.upper(root.get(User_.fullname)), "%" + entry.getValue().toString().toUpperCase() + "%");
                break;
            case "email":
                predicate = cb.like(cb.upper(root.get(User_.email)), "%" + entry.getValue().toString().toUpperCase() + "%");
                break;
            case "description":
                predicate = cb.like(cb.upper(root.get(BaseEntity_.description)), "%" + entry.getValue().toString().toUpperCase() + "%");
                break;
            case "groups":
                Object value = entry.getValue();
                if (value instanceof Object[]) {
                    Object[] listValue = (Object[]) value;
                    Predicate[] listPredicates = new Predicate[listValue.length];

                    for (int i = 0; i < listValue.length; i++) {
                        listPredicates[i] = cb.isMember((Group) listValue[i], root.get(User_.groups));
                    }
                    predicate = cb.and(listPredicates);
                } else if (value instanceof Group) {
                    predicate = cb.isMember((Group) value, root.get(User_.groups));
                }
                break;
        }

        return predicate;
    }

    protected String hashSHA256(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(value.getBytes("UTF-8"));
        byte[] hashed = md.digest();
        return Base64.getEncoder().encodeToString(hashed);
    }

    @Override
    public User updateUserPassword(User user, String oldPassword, String newPassword) {

        if (Validator.isNull(oldPassword)) {
            throw new EJBException("old-password-can-not-be-null");
        }

        if (Validator.isNull(newPassword)) {
            throw new EJBException("new-password-can-not-be-null");
        }

        String oldPasswordHashed = null;
        String newPasswordHashed = null;
        try {
            oldPasswordHashed = hashSHA256(oldPassword);
            newPasswordHashed = hashSHA256(newPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new EJBException("can-not-hash-password", ex);
        }

        if (user.getPassword().compareTo(oldPasswordHashed) == 0) {
            user.setPassword(newPasswordHashed);
            em.merge(user);
        } else {
            throw new EJBException("password-not-match");
        }

        return user;
    }

    @Override
    public User updateUserPassword(User user) {
        try {
            user.setPassword(hashSHA256(user.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new EJBException("can-not-hash(sha256)-password", e);
        }

        return update(user);
    }

    @Override
    public List<User> findAdministrators() {
        List<User> admins = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            cq.where(cb.isMember(Group.Admin, root.get(User_.groups)));

            TypedQuery<User> q = em.createQuery(cq);
            admins = q.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[UserServiceBean] findAdministrators()", e);
        }

        return admins;
    }
    
    protected Logger getLogger() {
        return LOGGER;
    }
}
