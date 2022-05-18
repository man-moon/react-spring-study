package com.ajou.prcoding.myweb;

import com.ajou.prcoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.myweb.dto.MusicList;
import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import com.ajou.prcoding.myweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
public class MusicController {

    @Autowired
    FavoriteRepository albumsRepo;


    @GetMapping(value = "/musicSearch/{term}")
    public MusicList musicSearchByPath(@PathVariable String term) {
        String url = "https://itunes.apple.com/search?term=";
        url += term;
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

    @GetMapping(value = "/musicSearch")
    public MusicList musicSearchByParam(@RequestParam String term) {
        String url = "https://itunes.apple.com/search?term=";
        url += term;
        url += "&entity=album";
        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        MusicList list = new MusicList();
        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;

        } catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    @GetMapping(value="/likes")
    public List<FavoriteMusic> getLikes() {

        try {
            return albumsRepo.findAll();

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    @PostMapping(value="/likes")
    @Transactional
    public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
        FavoriteMusic music = albumsRepo.save(favorite.toEntity());
        if(music != null) {
            return 1;
        }
        else {
            return 0;
        }
    }

}