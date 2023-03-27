package com.szxs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "dmw_item")
public class SearchItemListVo implements Serializable {

    private String abstractMessage;
    private String address;
    private Integer areaId;
    private String areaName;
    private Integer commentCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    private long createdTimeLong;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private long endTimeLong;
    private Integer id;
    private String imgUrl;
    private String itemName;
    private Integer itemTypeId1;
    private Integer itemTypeId2;
    private String latitude;
    private String longitude;
    private Double maxPrice;
    private Double minPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    private long startTimeLong;

    private List<String> keyword = new ArrayList<>();
}
