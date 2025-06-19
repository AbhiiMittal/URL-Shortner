package com.url.shortner.tinyurl.helper;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.url.shortner.tinyurl.model.BannedUrls;
import com.url.shortner.tinyurl.model.MasterDb;
import com.url.shortner.tinyurl.model.Urls;
import com.url.shortner.tinyurl.repository.BannedUrlsRepository;
import com.url.shortner.tinyurl.repository.MasterDbRepository;
import com.url.shortner.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class BloomFilterService {

    private BloomFilter<String> bloomFilter;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    private BannedUrlsRepository bannedUrlsRepository;

    @PostConstruct
    public void init() {
        long expectedInsertions = 100_000;
        double fpp = 0.01;

        bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), expectedInsertions, fpp);

        List<BannedUrls> bannedUrls = bannedUrlsRepository.findAll();
        for (BannedUrls urls : bannedUrls) {
            Urls url = urlRepository.findByUrlId(urls.getUrlId());
            bloomFilter.put(url.getShortcode());
        }
    }

    public boolean mightBeMalicious(String url) {
        return bloomFilter.mightContain(url);
    }

    public void addToFilter(String url) {
        bloomFilter.put(url);
    }
}
