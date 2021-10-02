package com.laioffer.market.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ZipCode")
public class ZipCode implements Serializable {

    private static final long serialVersionUID = -2455760938054036364L;

    @Id
    @Column(name = "zip_code")
    private String zipCode;

    private String city;

    private String state;

    @OneToMany(mappedBy = "zipCode", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
