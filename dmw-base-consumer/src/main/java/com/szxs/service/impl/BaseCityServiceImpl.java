package com.szxs.service.impl;

import com.alibaba.fastjson.JSON;
import com.szxs.entity.DmArea;
import com.szxs.feign.BaseClient;
import com.szxs.service.BaseCityService;
import com.szxs.util.EmptyUtil;
import com.szxs.vo.BaseCityVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BaseCityServiceImpl implements BaseCityService {
    @Autowired
    private BaseClient baseClient;

    public List<BaseCityVo> queryCity(DmArea dmArea) {
        try {
            if (EmptyUtil.isNotEmpty(dmArea)) {
                List<DmArea> dmAreaList = baseClient.queryCityList(dmArea);
                String jsonString = JSON.toJSONString(dmAreaList);
                List<BaseCityVo> cityVoList = JSON.parseArray(jsonString, BaseCityVo.class);
                return cityVoList;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
