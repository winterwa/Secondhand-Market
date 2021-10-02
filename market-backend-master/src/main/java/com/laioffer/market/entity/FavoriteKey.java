package com.laioffer.market.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavoriteKey implements Serializable {

    private static final long serialVersionUID = 2455760938054036111L;


    private String userId;


    private int postId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        FavoriteKey fk = (FavoriteKey) object;
        return this.userId.equals(fk.userId) && this.postId == fk.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}
