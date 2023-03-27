package com.szxs.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

/**
 * @Description TODO
 * @Author Rich
 * @Date 2022-10-25 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmItemComment implements Serializable {

	private Long id;

	/**
	 * 剧目Id
	 */
	private Long itemId;

	/**
	 * 评论用户ID
	 */
	private Long userId;

	/**
	 * 评分(1-10)
	 */
	private Integer score;

	/**
	 * 剧评
	 */
	private String content;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdTime;

	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedTime;
}
