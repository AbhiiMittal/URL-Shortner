package com.url.shortner.tinyurl.service;

import com.url.shortner.tinyurl.model.Urls;

public interface UrlService {
    public String createNewUrl(String url,Long userId);
    public String getOriginalUrl(String shortcode,Long userId);
}
