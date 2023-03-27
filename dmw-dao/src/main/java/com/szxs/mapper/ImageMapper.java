package com.szxs.mapper;

import com.szxs.entity.DmImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Rich
 * @create 2022/10/19 14:58
 */
@Mapper
public interface ImageMapper {

    DmImage queryDmImage(long id);

    int saveDmImage(DmImage dmImage);

}
