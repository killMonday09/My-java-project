package com.szxs.service;

import com.szxs.dto.SearchItemListDto;
import com.szxs.entity.Page;
import com.szxs.vo.SearchItemListVo;

import java.util.List;

public interface SearchItemListService {

    Page<SearchItemListVo> SearchItemListPage(SearchItemListDto searchItemListDto);
}
