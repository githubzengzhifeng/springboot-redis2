package com.unionman.springbootredis2.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhifeng.Zeng
 * @descrption
 * @date 2020/05/20 10:06
 */
@Component
@Slf4j
public class RedisConsumer extends Thread{

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    private void init(){
        new Thread(this).start();
    }

    @Override
    public void run(){
        while (true){
            Object topic_message = redisTemplate.opsForList().rightPop("TOPIC_MESSAGE", 10L, TimeUnit.SECONDS);
            if(topic_message!=null){
                System.out.println(topic_message.toString());
            }

        }
    }


}
