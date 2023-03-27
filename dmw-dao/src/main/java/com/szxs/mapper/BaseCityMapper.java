package com.szxs.mapper;

import com.szxs.entity.DmArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseCityMapper {

    List<DmArea> queryBaseCityList(DmArea dmArea);
}
