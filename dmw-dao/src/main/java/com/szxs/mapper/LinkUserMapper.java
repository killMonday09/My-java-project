package com.szxs.mapper;

import com.szxs.dto.LinkUserDto;
import com.szxs.entity.DmLinkUser;
import com.szxs.vo.LinkUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;

@Mapper
public interface LinkUserMapper {

    int addLinkUser(LinkUserDto linkUserDto);

    DmLinkUser queryLinkUserByIdCard(LinkUserDto linkUserDto);

    List<LinkUserVo> queryLinkUser(LinkUserDto linkUserDto);

    int deleteLinkUserById(LinkUserDto linkUserDto);

    DmLinkUser getLinkUser(@Param("id") Integer id);

}
