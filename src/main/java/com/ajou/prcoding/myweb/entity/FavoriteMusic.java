package com.ajou.prcoding.myweb.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="favoriteMusic")
@Getter
@Setter
@ToString
public class FavoriteMusic {
    public FavoriteMusic() {
    }

    @Id
    @Column(length = 32)
    private String collectionId;
    @Column
    private String collectionType;
    @Column
    private String artistId;
    @Column
    private String artistName;
    @Column
    private String artistViewUrl;
    @Column
    private String collectionName;
    @Column
    private String collectionViewUrl;
}
