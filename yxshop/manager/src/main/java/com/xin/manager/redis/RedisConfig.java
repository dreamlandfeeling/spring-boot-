package com.xin.manager.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
//public class RedisConfig {

    //@Bean
    //JedisConnectionFactory jedisConnectionFactory() {
    //    return new JedisConnectionFactory();
    //}

    //@Bean
    //public RedisTemplate<String, Account> redisTemplate(RedisConnectionFactory factory) {
    //    RedisTemplate<String, Account> template = new RedisTemplate<String, Account>();
    //    template.setConnectionFactory(factory);
    //    template.setKeySerializer(new StringRedisSerializer());
    //    template.setValueSerializer(new RedisObjectSerializer());
    //    return template;
    //}


//}