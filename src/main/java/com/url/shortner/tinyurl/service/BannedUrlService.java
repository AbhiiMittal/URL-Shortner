package com.url.shortner.tinyurl.service;

import com.url.shortner.tinyurl.model.BannedUrls;

import java.util.List;

public interface BannedUrlService {
    public List<BannedUrls> getUrl();
    public String createUrl(String url);
}
