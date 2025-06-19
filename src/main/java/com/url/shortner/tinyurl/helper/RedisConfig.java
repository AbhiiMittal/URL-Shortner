package com.url.shortner.tinyurl.helper;

import com.url.shortner.tinyurl.model.Urls;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Urls> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Urls> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use JSON serializer for value
        Jackson2JsonRedisSerializer<Urls> serializer = new Jackson2JsonRedisSerializer<>(Urls.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}

