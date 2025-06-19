package com.url.shortner.tinyurl.model;

import jakarta.persistence.*;

@Entity
public class MasterDb {
    @Id
    @SequenceGenerator(name = "master_seq", sequenceName = "MASTER_DB_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_seq")
    Long id;
    Long url_id;
    Long user_id;
    int is_banned;
    Long activity_id;

    public MasterDb(Long url_id, Long user_id, int is_banned,Long activity_id) {
        this.url_id = url_id;
        this.user_id = user_id;
        this.is_banned = is_banned;
        this.activity_id = activity_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUrl_id() {
        return url_id;
    }

    public void setUrl_id(Long url_id) {
        this.url_id = url_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }
}