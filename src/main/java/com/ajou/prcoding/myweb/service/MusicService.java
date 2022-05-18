package com.ajou.prcoding.myweb.service;

import com.ajou.prcoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.myweb.dto.MusicList;
import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import com.ajou.prcoding.myweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final FavoriteRepository albumsRepo;
    RestTemplate restTemplate = new RestTemplate();

    public MusicList searchMusic(String name) {
        String url = "https://itunes.apple.com/search?term=";
        url += name;
        url += "&entity=album";
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        MusicList list = new MusicList();
        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());

        } catch(IOException e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public List<FavoriteMusic> getLikes() {
        try {
            return albumsRepo.findAll();

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public int saveFavorite(FavoriteMusicRequestDto favorite) {
        FavoriteMusic music = albumsRepo.save(favorite.toEntity());
        if(music != null) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public String deleteFavorite(String id) {
        try {
            albumsRepo.deleteById(id);
            return "Delete completed";
        } catch (Exception e) {
            return "Not in Favorite Music";
        }
    }
}