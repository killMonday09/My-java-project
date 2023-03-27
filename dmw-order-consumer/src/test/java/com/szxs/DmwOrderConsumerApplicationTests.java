package com.szxs;

import com.szxs.util.DateUtil;
import com.szxs.util.OrderUtil;
import com.szxs.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class DmwOrderConsumerApplicationTests {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        int i = 12690;

        String s = redisUtil.get(i + ":" + "1_12");
        System.out.println(s);

    }



}
