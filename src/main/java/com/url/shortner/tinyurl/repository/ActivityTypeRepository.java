package com.url.shortner.tinyurl.repository;

import com.url.shortner.tinyurl.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType,Long> {
    ActivityType findByActivityType(String activityType);
}
