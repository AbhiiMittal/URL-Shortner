package com.url.shortner.tinyurl.model;

import jakarta.persistence.*;

@Entity
@Table(name="user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "user_data_seq",allocationSize = 1)
    @Column(name = "USER_ID")
    Long id;
    @Column(name = "USER_NAME")
    String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
