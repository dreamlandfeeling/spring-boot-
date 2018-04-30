package com.xin.top.redis;

import com.xin.top.model.TbUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    //@Bean
    //JedisConnectionFactory jedisConnectionFactory() {
    //    return new JedisConnectionFactory();
    //}

    //@Bean
    //public RedisTemplate<String, TbUser> redisTemplate(RedisConnectionFactory factory) {
    //    RedisTemplate<String, TbUser> template = new RedisTemplate<String, TbUser>();
    //    template.setConnectionFactory(factory);
    //    template.setKeySerializer(new StringRedisSerializer());
    //    template.setValueSerializer(new RedisObjectSerializer());
    //    return template;
    //}


}