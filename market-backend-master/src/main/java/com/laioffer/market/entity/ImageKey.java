package com.laioffer.market.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ImageKey implements Serializable {

    private static final long serialVersionUID = 2455760938054036222L;

    private int postId;

    private String url;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        ImageKey ik = (ImageKey) object;
        return this.postId == ik.postId && this.url.equals(ik.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, url);
    }
}
