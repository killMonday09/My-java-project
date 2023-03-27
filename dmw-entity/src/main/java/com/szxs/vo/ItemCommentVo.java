package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Rich
 * @create 2022/10/25 11:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCommentVo implements Serializable {
    private Integer itemId;
    private Integer userId;
    private String userName;
    private Integer score;
    private String content;
    private LocalDateTime createdTime;
    private String imgUrl;

}
