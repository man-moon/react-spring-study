package com.ajou.prcoding.myweb.repository;

import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FavoriteRepository extends JpaRepository<FavoriteMusic, String> {
    List<FavoriteMusic> findAll();
    void deleteById(String id);

    @Query(value = "select distinct fm.artist_name, fm.artist_view_url from favorite_music fm", nativeQuery = true)
    List<Map<String, String>> findDistinctByArtistName();
}
