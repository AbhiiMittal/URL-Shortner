package com.url.shortner.tinyurl.controller;

import com.url.shortner.tinyurl.model.ShorturlRequestDTO;
import com.url.shortner.tinyurl.model.UrlRequestDTO;
import com.url.shortner.tinyurl.model.Urls;
import com.url.shortner.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    UrlService urlService;
    @GetMapping("/get-original-url/{userId}")
    public ResponseEntity<?> getUrl(@RequestBody ShorturlRequestDTO shorturlRequestDTO, @PathVariable Long userId){
        String shortcode = shorturlRequestDTO.getShortcode();
        try{
            String urls = urlService.getOriginalUrl(shortcode, userId);
            if (urls == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Url Not Found");
            }
            return ResponseEntity.ok(urls);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.REQUEST_TIMEOUT)
                    .body(e.getMessage());
        }
    }
    @PostMapping("/create-new-url/{userId}")
    public ResponseEntity<?> createNewUrl(@RequestBody UrlRequestDTO urlRequestDTO,@PathVariable Long userId){
        try{
            return ResponseEntity.ok(urlService.createNewUrl(urlRequestDTO.getUrl(), userId));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.REQUEST_TIMEOUT)
                    .body(e.getMessage());

        }
    }
}
