package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchItemListDto implements Serializable {
    private Integer areaId;
    private Integer currentPage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    private Integer itemTypeId1;
    private Integer itemTypeId2;
    private Integer pageSize;
    private String sort;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    private String keyword;
}
