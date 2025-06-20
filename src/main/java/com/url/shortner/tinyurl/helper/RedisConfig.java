package com.url.shortner.tinyurl.helper;

import com.url.shortner.tinyurl.model.UrlResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, UrlResponseDTO> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, UrlResponseDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use JSON serializer for value
        Jackson2JsonRedisSerializer<UrlResponseDTO> serializer = new Jackson2JsonRedisSerializer<>(UrlResponseDTO.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}

