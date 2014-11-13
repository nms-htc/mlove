/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.web.admin;

import com.nms.mlove.entity.User;
import com.nms.mlove.service.UserService;
import com.nms.mlove.util.MessageUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MinhDT
 */
@Named
@SessionScoped
public class AuthenticateController implements Serializable {

    private static final long serialVersionUID = -8568465506043397830L;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String originalURL;

    @EJB
    private UserService service;

    private User currentUser;

    @Produces
    public User productUser() {
        return currentUser;
    }

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    public void login() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(getUsername(), getPassword());
            User user = service.findByUP(getUsername(), getPassword());
            externalContext.getSessionMap().put("currentUser", user);
            currentUser = user;
            externalContext.redirect(originalURL);
        } catch (ServletException e) {
            // handle unknow username/password in request.login()
            String errorMessage = MessageUtil.getBundleMessage("user-pass-incorrect");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
            facesContext.addMessage(null, message);
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
