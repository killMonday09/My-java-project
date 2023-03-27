package com.szxs.mapper;

import com.szxs.dto.SearchItemTypeDto;
import com.szxs.entity.DmItem;
import com.szxs.entity.DmItemType;
import com.szxs.vo.FloorItemVo;
import com.szxs.vo.FloorVo;
import com.szxs.vo.ItemTypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemTypeMapper {

    /**
     * 查询所有分类集合
     * @param dmItemType
     * @return
     */
    List<DmItemType> queryItemTypeList(DmItemType dmItemType);

    /**
     * 查询推荐电影1F/2F
     * @param FloorVo
     * @return
     */
    List<FloorVo> queryFloorList(FloorVo FloorVo);

    /**
     * 查询推荐电影1F/2F集合
     * @param dmItem
     * @return
     */
    List<DmItem> queryFloorItemList(DmItem dmItem);

    /**
     * 全文检索查询分类
     * @return
     */
    List<ItemTypeVo> queryItemType(SearchItemTypeDto dto);
}
