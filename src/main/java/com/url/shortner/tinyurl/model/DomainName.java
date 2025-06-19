package com.url.shortner.tinyurl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DOMAINS")
public class DomainName {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="DomainSEQ")
    @SequenceGenerator(name = "DomainSEQ", sequenceName = "DOMAINSEQ", allocationSize = 1)
    Long id;
    @Column(name = "DOMAINNAME")
    String domainName;

    public DomainName() {
    }

    public DomainName(String domainName) {
        this.domainName = domainName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
