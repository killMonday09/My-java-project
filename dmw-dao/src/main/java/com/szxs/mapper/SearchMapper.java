package com.szxs.mapper;


import com.szxs.dto.SearchItemListDto;
import com.szxs.vo.SearchItemListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface SearchMapper {

    /**
     * 全文检索
     * @param offsetTime
     * @return
     */
    List<SearchItemListVo> queryItemList(@Param("offsetTime") String offsetTime);


}
