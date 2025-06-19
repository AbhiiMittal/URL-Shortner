package com.url.shortner.tinyurl.model;

import jakarta.persistence.*;

@Entity
public class Urls {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "url_seq")
    @SequenceGenerator(name = "url_seq",sequenceName = "url_seq",allocationSize = 1)
    @Column(name = "URL_ID")
    Long urlId;
    @Column(name = "URL")
    String originalUrl;
    String shortcode;
    @Column(name = "DOMAINID")
    Long DomainId;
    @Column(name = "PROTOCOLID")
    Long ProtocolId;


    public Urls() {
    }

    public Urls(String originalUrl, String shortcode, Long domainId, Long protocolId) {
        this.originalUrl = originalUrl;
        this.shortcode = shortcode;
        DomainId = domainId;
        ProtocolId = protocolId;
    }

    public Urls(String url, String shortcode) {
        this.originalUrl = url;
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

    public void setOriginalUrl(String original_url) {
        this.originalUrl = original_url;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public Long getDomainId() {
        return DomainId;
    }

    public void setDomainId(Long domainId) {
        DomainId = domainId;
    }

    public Long getProtocolId() {
        return ProtocolId;
    }

    public void setProtocolId(Long protocolId) {
        ProtocolId = protocolId;
    }
}
