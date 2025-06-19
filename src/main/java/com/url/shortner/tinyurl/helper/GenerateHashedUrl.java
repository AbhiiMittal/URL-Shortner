package com.url.shortner.tinyurl.helper;

import com.url.shortner.tinyurl.model.Urls;
import com.url.shortner.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.CRC32;

@Service
public class GenerateHashedUrl {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String suffix = "a";

    @Autowired
    UrlRepository urlRepository;

    public String generateShortUrl(String originalUrl){
        CRC32 crc = new CRC32();
        crc.update(originalUrl.getBytes());
        long crcValue = crc.getValue();
        StringBuilder shortcode = new StringBuilder(toBase62(crcValue));
        List<Urls> urls = urlRepository.findByShortcodeStartingWith(shortcode.toString());
        for(Urls url : urls){
            if(url.getOriginalUrl().equals(originalUrl)){
                return url.getShortcode();
            }
        }
        int num = urls.size();
        while(num--!=0){
            shortcode.append('a');
        }
        return shortcode.toString();
    }
    private static String toBase62(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(BASE62.charAt((int)(value % 62)));
            value /= 62;
        }
        return sb.reverse().toString();
    }
}
