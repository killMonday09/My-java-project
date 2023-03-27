package com.szxs.service;

import com.szxs.entity.DmArea;
import com.szxs.vo.BaseCityVo;

import java.util.List;

public interface BaseCityService {

    List<BaseCityVo> queryCity(DmArea dmArea);
}
