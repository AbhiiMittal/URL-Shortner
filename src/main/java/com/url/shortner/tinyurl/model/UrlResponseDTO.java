package com.url.shortner.tinyurl.model;

import jakarta.persistence.Column;

public class UrlResponseDTO {
    Long urlId;
    String originalUrl;
    String shortcode;

    public UrlResponseDTO(Long urlId, String originalUrl, String shortcode) {
        this.urlId = urlId;
        this.originalUrl = originalUrl;
        this.shortcode = shortcode;
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
}
