package com.ajou.prcoding.myweb.dto;

import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteMusicRequestDto {
    private String collectionId;
    private String collectionType;
    private String artistId;
    private String artistName;
    private String artistViewUrl;
    private String collectionName;
    private String collectionViewUrl;
    private String collectionCensoredName;
    private String artworkUrl100;

    public FavoriteMusic toEntity() {
        FavoriteMusic music = new FavoriteMusic();

        music.setCollectionId(this.collectionId);
        music.setCollectionType(this.collectionType);
        music.setArtistId(this.artistId);
        music.setArtistName(this.artistName);
        music.setArtistViewUrl(this.artistViewUrl);
        music.setCollectionName(this.collectionName);
        music.setCollectionViewUrl(this.collectionViewUrl);
        music.setCollectionCensoredName(this.collectionCensoredName);
        music.setArtworkUrl100(this.artworkUrl100);

        return music;
    }
}
