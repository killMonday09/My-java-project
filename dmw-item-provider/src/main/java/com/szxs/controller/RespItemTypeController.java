package com.szxs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szxs.dto.SearchItemTypeDto;
import com.szxs.entity.DmItem;
import com.szxs.entity.DmItemType;
import com.szxs.mapper.ItemTypeMapper;
import com.szxs.vo.FloorItemVo;
import com.szxs.vo.FloorVo;
import com.szxs.vo.ItemTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RespItemTypeController {
    @Autowired
    private ItemTypeMapper itemTypeMapper;

    @PostMapping("/queryItemTypeList")
    public List<DmItemType> queryItemTypeList(@RequestBody DmItemType itemType){
        return itemTypeMapper.queryItemTypeList(itemType);
    }

    @PostMapping("/queryFloorList")
    public List<FloorVo> queryFloorList(@RequestBody FloorVo floorVo){
        return itemTypeMapper.queryFloorList(floorVo);
    }

    @PostMapping("/queryFloorItems")
    public List<FloorItemVo> queryFloorItemList(@RequestBody DmItem dmItem,
                                                @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                                @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo pageInfo = new PageInfo(itemTypeMapper.queryFloorItemList(dmItem));
        return pageInfo.getList();
    }

    /**
     * 全文检索分类
     */
    @PostMapping("/queryItemType")
    public List<ItemTypeVo> queryItemType(@RequestBody SearchItemTypeDto searchItemTypeDto){
        return itemTypeMapper.queryItemType(searchItemTypeDto);
    }

}
