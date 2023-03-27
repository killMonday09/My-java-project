package com.szxs.feign;

import com.szxs.entity.DmArea;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("dmw-base-provider")
public interface BaseClient {

    /**
     * 查询城市
     * @param dmArea
     * @return
     */
    @PostMapping("/queryBaseCityList")
    List<DmArea> queryCityList(@RequestBody DmArea dmArea);
}
