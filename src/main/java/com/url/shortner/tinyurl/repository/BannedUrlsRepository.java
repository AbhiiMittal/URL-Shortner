package com.url.shortner.tinyurl.repository;


import com.url.shortner.tinyurl.model.BannedUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedUrlsRepository extends JpaRepository<BannedUrls,Long> {
    public BannedUrls findByUrlId(Long urlId);
}
