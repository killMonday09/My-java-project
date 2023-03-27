package com.szxs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<SearchItemListVo>{
    private int beginPos;
    private int curPage;
    private int pageCount;
    private int pageSize;
    private int total;
    private List<SearchItemListVo> rows;

}
