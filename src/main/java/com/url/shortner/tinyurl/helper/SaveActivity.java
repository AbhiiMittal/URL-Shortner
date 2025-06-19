package com.url.shortner.tinyurl.helper;

import com.url.shortner.tinyurl.model.ActivityType;
import com.url.shortner.tinyurl.model.BannedUrls;
import com.url.shortner.tinyurl.model.MasterDb;
import com.url.shortner.tinyurl.model.Urls;
import com.url.shortner.tinyurl.repository.ActivityTypeRepository;
import com.url.shortner.tinyurl.repository.BannedUrlsRepository;
import com.url.shortner.tinyurl.repository.MasterDbRepository;
import com.url.shortner.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveActivity {

    @Autowired
    MasterDbRepository masterDbRepository;

    @Autowired
    ActivityTypeRepository activityTypeRepository;

    @Autowired
    BannedUrlsRepository bannedUrlsRepository;

    @Autowired
    UrlRepository urlRepository;

    public void save(Long urlId,Long user_id,String url,String shortcode,boolean is_banned,String activityType){
        ActivityType activityTypes = activityTypeRepository.findByActivityType(activityType);
        Long id = activityTypes.getId();
        if(!is_banned){
            BannedUrls bannedUrls = bannedUrlsRepository.findByUrlId(urlId);
            if (bannedUrls != null) {
                is_banned = true;
            }
        }
        MasterDb masterDb = new MasterDb(user_id,urlId,is_banned? 1 : 0,id);
        masterDbRepository.save(masterDb);
    }
}
