package com.systemdesign.urlshortener.controller;

import com.systemdesign.urlshortener.model.BigURLData;
import com.systemdesign.urlshortener.service.URLShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class URLShortenerController {

    @Autowired
    private URLShortenerService urlShortenerService;

    @GetMapping("/getBigURL/{shortUrl}")
    String getBigURL(@PathVariable(name = "shortUrl") String shortURL) {
        return urlShortenerService.getBigURL(shortURL);
    }

    @PostMapping("/addBigURL")
    String addBigURL(@RequestBody BigURLData bigURLData) {
        log.info("Request body is {}", bigURLData);
        return urlShortenerService.addBigURL(bigURLData.getBigURL());
    }
}
