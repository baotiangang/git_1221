package com.bao.springboot.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {

    /**
     * RedisTemplate 泛型：要么不写
     *  <String, String>  <Object, Object> 要么只能用这两种
     */
    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/redis/addString")
    public String addToRedis(){

        //valueOperations操作字符串的
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //添加
        valueOperations.set("name","xiaoqingnan");

        return "redis添加数据";
    }

    @RequestMapping("/redis/getKey")
    public String getkey(){

        //valueOperations操作字符串的
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //添加
        Object value = valueOperations.get("name");

        return "key是：name " +"value是：" +value;
    }

    /**
     * 设置value的序列化
     */

    @RequestMapping(value = "/redis/serialize")
    public String serialize(String k, String v){

        //key 序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //value 序列化
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.opsForValue().set(k,v);

        return "添加成功";
    }
}
