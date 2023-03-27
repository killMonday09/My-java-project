package com.szxs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DmwSchedulerProviderApplicationTests {

    @Test
    void contextLoads() {
        int x = 1;
        int y = 2;
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append("_");
        sb.append(y);
        sb.append(",");
        System.out.println(sb+"------------------");
    }

}
