package com.url.shortner.tinyurl.service;

import com.url.shortner.tinyurl.helper.BloomFilterService;
import com.url.shortner.tinyurl.helper.GenerateHashedUrl;
import com.url.shortner.tinyurl.helper.SaveActivity;
import com.url.shortner.tinyurl.model.BannedUrls;
import com.url.shortner.tinyurl.model.DomainName;
import com.url.shortner.tinyurl.model.ProtocolsType;
import com.url.shortner.tinyurl.model.Urls;
import com.url.shortner.tinyurl.repository.BannedUrlsRepository;
import com.url.shortner.tinyurl.repository.DomainsNameRepository;
import com.url.shortner.tinyurl.repository.ProtocolTypeRepository;
import com.url.shortner.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    SaveActivity saveActivity;

    @Autowired
    GenerateHashedUrl generateHashedUrl;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    BannedUrlsRepository bannedUrlsRepository;

    @Autowired
    BloomFilterService bloomFilterService;

    @Autowired
    CachingData cachingData;

    @Autowired
    DomainsNameRepository domainsNameRepository;

    @Autowired
    ProtocolTypeRepository protocolTypeRepository;

    @Override
    public String createNewUrl(String url,Long userId) {
        try{
            String shortcode = generateHashedUrl.generateShortUrl(url);
            Urls urls = cachingData.checkingInRedis(shortcode);
            if (urls != null) {
                Long urlId = urls.getUrlId();
                if(checkIfUrlBanned(shortcode)){
                    bloomFilterService.addToFilter(shortcode);
                    saveActivity.save(urlId,userId, url, shortcode, true,"createNewUrl");
                    return "Url is Banned";
                }
                saveActivity.save(urlId,userId, url, shortcode, false, "createNewUrl");
                return shortcode;
            }
            Urls urls1 = saveNewUrl(url, shortcode);
            saveActivity.save(urls1.getUrlId(),userId, url, shortcode, false, "createNewUrl");
            return shortcode;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getOriginalUrl(String shortcode,Long userId) {
        try{
            Urls urls = cachingData.checkingInRedis(shortcode);
            if (urls != null) {
                Long urlId = urls.getUrlId();
                String originalUrl = urls.getOriginalUrl();
                if(bloomFilterService.mightBeMalicious(shortcode)){
                    BannedUrls bannedUrls = bannedUrlsRepository.findByUrlId(urls.getUrlId());
                    if(bannedUrls!=null){
                        saveActivity.save(urlId,userId, originalUrl, shortcode, true,"getOriginalUrl");
                        return "URL IS BANNED";
                    }
                }else{
                    saveActivity.save(urlId,userId, originalUrl, shortcode, false,"getOriginalUrl");
                }
                return originalUrl;
            }
            return null;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Urls saveNewUrl(String originalUrl,String shortcode){
        try{
            URI url = new URI(originalUrl);
            String host = url.getHost();
            String protocol = url.getScheme();
            String path = url.getPath();
            DomainName domainName = new DomainName(host);
            ProtocolsType protocolsType = new ProtocolsType(protocol);
            domainsNameRepository.save(domainName);
            protocolTypeRepository.save(protocolsType);
            Urls urls = new Urls(path, shortcode,domainName.getId(),protocolsType.getId());
            urlRepository.save(urls);
            return urls;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Urls findUrlExists(String url){
        try{
            return urlRepository.findByOriginalUrl(url);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean checkIfUrlBanned(String originalUrl){
        String shortcode = generateHashedUrl.generateShortUrl(originalUrl);
        List<Urls> listOfCodes = urlRepository.findByShortcodeStartingWith(shortcode);
        for(Urls url : listOfCodes){
            if(url.getOriginalUrl().equals(originalUrl)){
                return bannedUrlsRepository.findByUrlId(url.getUrlId()) != null;
            }
        }
        return false;
    }
}