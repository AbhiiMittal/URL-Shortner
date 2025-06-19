package com.url.shortner.tinyurl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BANNED")
public class BannedUrls {
    @Id
    @SequenceGenerator(name = "BANNED_SEQ", sequenceName = "BANNED_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANNED_SEQ")
    Long id;
    @Column(name = "URLID")
    Long urlId;
    @Column(name = "ISBANNED")
    int isBanned;

    public BannedUrls() {
    }

    public BannedUrls(Long urlId, int isBanned) {
        this.urlId = urlId;
        this.isBanned = isBanned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }
}
