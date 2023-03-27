package com.szxs.controller;

import com.szxs.mapper.SearchMapper;
import com.szxs.vo.SearchItemListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemSerachController {
    @Autowired
    private SearchMapper searchMapper;

    /**
     * 全文检索
     * @param offsetTime
     * @return
     */
    @PostMapping("/querySearchItemList")
    public List<SearchItemListVo> querySearchItemList(@Param("offsetTime") String offsetTime){
        List<SearchItemListVo> itemListVoList = searchMapper.queryItemList(offsetTime);
        return itemListVoList;
    }
}
