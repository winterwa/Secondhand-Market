package com.laioffer.market.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Favorite")
public class Favorite implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @EmbeddedId
    private FavoriteKey favoriteKey;


    @Basic
    @Temporal(TemporalType.TIME)
    private Date favoriteTime;

    public FavoriteKey getFavoriteKey() {
        return favoriteKey;
    }

    public void setFavoriteKey(FavoriteKey favoriteKey) {
        this.favoriteKey = favoriteKey;
    }

    public Date getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(Date favoriteTime) {
        this.favoriteTime = favoriteTime;
    }
}
