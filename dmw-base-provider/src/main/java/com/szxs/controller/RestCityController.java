package com.szxs.controller;

import com.szxs.entity.DmArea;
import com.szxs.mapper.BaseCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCityController {
    @Autowired
    private BaseCityMapper baseCityMapper;

    /**
     * 查询城市
     * @param dmArea
     * @return
     */
    @PostMapping("/queryBaseCityList")
    List<DmArea> queryCityList(@RequestBody DmArea dmArea){
        return baseCityMapper.queryBaseCityList(dmArea);
    }
}
