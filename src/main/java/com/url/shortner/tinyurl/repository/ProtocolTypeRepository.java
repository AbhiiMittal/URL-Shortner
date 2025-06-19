package com.url.shortner.tinyurl.repository;

import com.url.shortner.tinyurl.model.ProtocolsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolTypeRepository extends JpaRepository<ProtocolsType,Long> {
}
