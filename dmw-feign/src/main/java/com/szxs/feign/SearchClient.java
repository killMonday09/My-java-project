package com.szxs.feign;


import com.szxs.vo.SearchItemListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("dmw-search-provider")
public interface SearchClient {

    /**
     * 全文检索
     * @param offsetTime
     * @return
     */
    @PostMapping("/querySearchItemList")
    List<SearchItemListVo> querySearchItemList(@Param("offsetTime") String offsetTime);
}
