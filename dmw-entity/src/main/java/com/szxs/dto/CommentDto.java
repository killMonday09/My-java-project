package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Rich
 * @create 2022/10/25 11:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {
    private Integer id;

    private Integer itemId;
}
