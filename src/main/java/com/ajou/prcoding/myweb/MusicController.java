package com.ajou.prcoding.myweb;

import com.ajou.prcoding.myweb.dto.MusicList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class MusicController {

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
}
