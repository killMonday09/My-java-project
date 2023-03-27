package com.szxs.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

/**
 * @Description TODO
 * @Author Rich
 * @Date 2022-10-19 
 */
public class DmImage implements Serializable {

	private Long id;

	/**
	 * 图片URL
	 */
	private String imgUrl;

	/**
	 * 关联表ID
	 */
	private Integer targetId;

	/**
	 * 顺序
	 */
	private Integer sort;

	/**
	 * 图片类型（0:无类型 1:轮播图 2:海报图）
	 */
	private Integer type;

	/**
	 * 图片分类（0：用户头像 1：商品图片）
	 */
	private Integer category;

	/**
	 * 创建时间
	 */
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdTime;

	/**
	 * 更新时间
	 */
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedTime;

	public DmImage(Long id) {
		this.id = id;
	}

	public DmImage() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public LocalDateTime getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

}
