package com.url.shortner.tinyurl.controller;


import com.url.shortner.tinyurl.model.UrlRequestDTO;
import com.url.shortner.tinyurl.service.BannedUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api")
public class BannedUrlController {

    @Autowired
    BannedUrlService bannedUrlService;

    @PostMapping("/add-new-url")
    public ResponseEntity<?> addNewUrl(@RequestBody UrlRequestDTO urlRequestDTO){
        try{
            String url = urlRequestDTO.getUrl();
            bannedUrlService.createUrl(url);
            return ResponseEntity.ok().body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-url")
    public ResponseEntity<?> getUrl(){
        try{
            return ResponseEntity.ok().body(bannedUrlService.getUrl());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
