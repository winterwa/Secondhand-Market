package com.laioffer.market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Image")
public class Image implements Serializable {

    private static final long serialVersionUID = 7551999649936522523L;

    public Image() {
    }

    public Image(String imageUrl, int postId) {
        this.imageKey = new ImageKey();
        this.imageKey.setUrl(imageUrl);
        this.imageKey.setPostId(postId);
    }

    @EmbeddedId
    private ImageKey imageKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JsonIgnore
    private Post post;

    public ImageKey getImageKey() {
        return imageKey;
    }

    public void setImageKey(ImageKey imageKey) {
        this.imageKey = imageKey;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
