package com.url.shortner.tinyurl.repository;

import com.url.shortner.tinyurl.model.MasterDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDbRepository extends JpaRepository<MasterDb,Long> {
}
