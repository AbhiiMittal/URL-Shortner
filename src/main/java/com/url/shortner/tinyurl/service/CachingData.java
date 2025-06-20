package com.url.shortner.tinyurl.service;

import com.url.shortner.tinyurl.model.DomainName;
import com.url.shortner.tinyurl.model.ProtocolsType;
import com.url.shortner.tinyurl.model.UrlResponseDTO;
import com.url.shortner.tinyurl.model.Urls;
import com.url.shortner.tinyurl.repository.DomainsNameRepository;
import com.url.shortner.tinyurl.repository.ProtocolTypeRepository;
import com.url.shortner.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class CachingData {
    private static final Duration SLIDING_TTL = Duration.ofMinutes(10);
    private static final String PREFIX = "cachedShortUrls::";

    @Autowired
    private RedisTemplate<String, UrlResponseDTO> redisTemplate;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    DomainsNameRepository domainsNameRepository;

    @Autowired
    ProtocolTypeRepository protocolTypeRepository;

    public UrlResponseDTO checkingInRedis(String shortcode){
        String key = PREFIX + shortcode;

        UrlResponseDTO url = redisTemplate.opsForValue().get(key);
        if(url!=null){
            redisTemplate.expire(key,SLIDING_TTL);
            return url;
        }
        return findIfUrlExists(shortcode);
    }
    @Cacheable(value = "cachedShortUrls",key = "#shortcode")
    public UrlResponseDTO findIfUrlExists(String shortcode){
        return findShortUrlExists(shortcode);
    }

    public UrlResponseDTO findShortUrlExists(String url){
        try{
            Urls urls = urlRepository.findByShortcode(url);
            if(urls==null) return null;
            UrlResponseDTO urlResponseDTO = new UrlResponseDTO(urls.getUrlId(), urls.getOriginalUrl(), urls.getShortcode());
            Long domainId = urls.getDomainId();
            Long protocolId = urls.getProtocolId();
            Optional<ProtocolsType> protocolsType = protocolTypeRepository.findById(protocolId);
            if(protocolsType.isEmpty()) throw new RuntimeException("protocol type is missing");

            Optional<DomainName> domainName = domainsNameRepository.findById(domainId);
            if(domainName.isEmpty()) throw new RuntimeException("domain name is missing");

            String protocolType = protocolsType.get().getProtocolName();
            String domain = domainName.get().getDomainName();
            String path = urls.getOriginalUrl();
            String finalUrl = protocolType +"://"+domain+path;
            urlResponseDTO.setOriginalUrl(finalUrl);
            return urlResponseDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
