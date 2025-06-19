package com.url.shortner.tinyurl.repository;

import com.url.shortner.tinyurl.model.Urls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Urls,Long> {
    Urls findByOriginalUrl(String originalUrl);
    Urls findByShortcode(String shortcode);
    List<Urls> findByShortcodeStartingWith(String shortcode);
    Urls findByUrlId(Long urlId);
}