package com.url.shortner.tinyurl.service;

import com.url.shortner.tinyurl.helper.BloomFilterService;
import com.url.shortner.tinyurl.helper.GenerateHashedUrl;
import com.url.shortner.tinyurl.model.BannedUrls;
import com.url.shortner.tinyurl.model.Urls;
import com.url.shortner.tinyurl.repository.BannedUrlsRepository;
import com.url.shortner.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class BannedUrlServiceImpl implements BannedUrlService{

    @Autowired
    BannedUrlsRepository bannedUrlsRepository;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    GenerateHashedUrl generateHashedUrl;

    @Autowired
    BloomFilterService bloomFilterService;

    @Override
    public List<BannedUrls> getUrl() {
        return bannedUrlsRepository.findAll();
    }

    @Override
    public String createUrl(String url) {
        try{
            URI uri = new URI(url);
            Urls urls = urlRepository.findByOriginalUrl(uri.getPath());
            Long id = urls != null ? urls.getUrlId() : 0L;
            if (urls == null) {
                String shortcode = generateHashedUrl.generateShortUrl(url);
                Urls urls1 = urlRepository.save(new Urls(url, shortcode));
                id = urls1.getUrlId();
                bloomFilterService.addToFilter(shortcode);
            }
            bannedUrlsRepository.save(new BannedUrls(id, 1));
            return "created successfully";
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
