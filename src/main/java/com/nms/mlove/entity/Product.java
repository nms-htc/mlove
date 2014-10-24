package com.nms.mlove.entity;

import com.nms.mlove.util.StringUtil;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nguyen Trong Cuong
 * @since 09/10/2014
 * @version 1.0
 */
@Entity
@Table(name = "ML_PRODUCT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@XmlRootElement
public abstract class Product extends BaseEntity {

    private static final long serialVersionUID = 4563350444559459751L;

    @NotNull
    @Size(max = 150)
    @Column(name = "TITLE", length = 150, nullable = false)
    protected String title;

    @Size(max = 4000)
    @Column(name = "CONTENT", length = 2000)
    private String content;

    @NotNull
    @Size(max = 200)
    @Column(name = "URL_TITLE", length = 200, nullable = false)
    protected String friendlyTitle;

    @Min(0)
    @Column(name = "VIEW_COUNT", length = 4)
    protected int viewCount;

    @Min(0)
    @Column(name = "DOWNLOAD_COUNT", length = 4)
    protected int downCount;

    @Min(0)
    @Column(name = "PRICE")
    protected double price;

    @Column(name = "PROMOTION")
    protected boolean promotion;
    
    @Min(0)
    @Column(name = "PROMO_PRICE")
    private double promoPrice;
    
    @ManyToOne
    @JoinColumn(name = "CAT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Cat cat;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFriendlyTitle() {
        return friendlyTitle;
    }

    public void setFriendlyTitle(String friendlyTitle) {
        this.friendlyTitle = friendlyTitle;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public void setPromoPrice(double promoPrice) {
        this.promoPrice = promoPrice;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPromoPrice() {
        return promoPrice;
    }
    
    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "BaseProduct{" + "title=" + title + ", friendlyTitle=" + friendlyTitle
                + ", viewCount=" + viewCount + ", downCount=" + downCount + ", price=" + price
                + ", promotion=" + promotion + ", promoPrice=" + getPromoPrice() + '}';
    }

    @PrePersist
    @PreUpdate
    protected void makeFriendlyTitle() {
        friendlyTitle = StringUtil.toUrlFriendly(title);
    }
}
