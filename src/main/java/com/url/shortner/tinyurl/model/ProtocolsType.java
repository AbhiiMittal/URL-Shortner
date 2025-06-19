package com.url.shortner.tinyurl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROTOCOLSTYPE")
public class ProtocolsType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ProtocolsTypeSEQ")
    @SequenceGenerator(name = "ProtocolsTypeSEQ", sequenceName = "PROTOCOLSTYPESEQ", allocationSize = 1)
    Long id;

    public ProtocolsType() {
    }

    @Column(name = "PROTOCOLNAME")
    String protocolName;

    public ProtocolsType(String protocolName) {
        this.protocolName = protocolName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }
}
