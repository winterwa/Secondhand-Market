package com.laioffer.market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Post")
public class Post implements Serializable {
    private static final long serialVersionUID = 8436097833452420298L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(name = "item_name")
    private String itemName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 10000, columnDefinition="Text")
    private String description;

    @Column(name = "preview_image_url")
    private String previewImageUrl;

    private double price;

    @ManyToOne
    @JsonIgnore
    private ZipCode zipCode;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @Basic
    @Temporal(TemporalType.TIME)
    @Column(name = "post_time")
    private Date postTime;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> detailedImageUrls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }

    public void setPreviewImageUrl(String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getDetailedImageUrls() {
        return detailedImageUrls;
    }

    public void setDetailedImageUrls(List<Image> detailedImageUrls) {
        this.detailedImageUrls = detailedImageUrls;
    }
}
