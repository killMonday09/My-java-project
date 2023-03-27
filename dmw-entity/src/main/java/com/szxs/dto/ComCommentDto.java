package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Rich
 * @create 2022/10/25 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComCommentDto implements Serializable {
    private String comment;
    private Integer itemId;
    private Integer score;
    private Integer  userId;
    private String content;
}
