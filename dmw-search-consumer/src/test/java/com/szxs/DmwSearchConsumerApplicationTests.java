package com.szxs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@SpringBootTest
class DmwSearchConsumerApplicationTests {

    @Test
    void contextLoads() {
//        LocalDateTime ldt= new Date(1666666666).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
//        System.out.println(ldt);

        String seats = "1_12,1_13";
        String[] split = seats.split(",");
        for (int i = 0; i < split.length; i++) {
            String[] seat = split[i].split("_");
            System.out.println(seat+"----------------------");
        }
    }

}
