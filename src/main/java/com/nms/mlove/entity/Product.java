package com.nms.mlove.entity;

import com.nms.mlove.util.StringUtil;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
    @Column(name = "TITLE", length = 150, unique = true, nullable = false)
    protected String title;

    @Size(max = 4000)
    @Column(name = "CONTENT", length = 2000)
    protected String content;

    @NotNull
    @Size(max = 200)
    @Column(name = "URL_TITLE", length = 200, unique = true, nullable = false)
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
    protected boolean promoPrice;

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

    public boolean isPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(boolean promoPrice) {
        this.promoPrice = promoPrice;
    }

    @Override
    public String toString() {
        return "BaseProduct{" + "title=" + title + ", friendlyTitle=" + friendlyTitle
                + ", viewCount=" + viewCount + ", downCount=" + downCount + ", price=" + price
                + ", promotion=" + promotion + ", promoPrice=" + promoPrice + '}';
    }

    @PrePersist
    @PreUpdate
    protected void makeFriendlyTitle() {
        friendlyTitle = StringUtil.toUrlFriendly(title);
    }
}
