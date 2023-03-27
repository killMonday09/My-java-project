package com.szxs;

import com.szxs.util.MailUtil;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class DmwUserConsumerApplicationTests {

    @Test
    void contextLoads() {
        MailUtil.sendMail("2350614808@qq.com","123456");
    }

}
