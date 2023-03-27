package com.szxs.service;

import com.szxs.dto.ComCommentDto;
import com.szxs.dto.DmUserDto;
import com.szxs.dto.LinkUserDto;
import com.szxs.dto.ModifyUserDto;
import com.szxs.dto.RegisterDto;
import com.szxs.entity.DmLinkUser;
import com.szxs.entity.DmUser;
import com.szxs.vo.DmUserVo;
import com.szxs.vo.LinkUserVo;
import com.szxs.vo.UserVoById;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserLoginService {

//    List<Object> queryLogin(DmUserDto dmUser);

    /**
     * 登录
     * @param dmUser
     * @return
     */
    List<Object> Login(DmUserDto dmUser);

    /**
     * 注册
     * @param registerDto
     * @return
     */
    boolean register(@RequestBody RegisterDto registerDto );

    /**
     * code
     * @param registerDto
     * @return
     */
    boolean code(@RequestBody RegisterDto registerDto);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserVoById queryById(@RequestParam("id") Integer id);

    /**
     * 修改
     * @param modifyUserDto
     * @return
     */
    boolean updateUser(@RequestBody ModifyUserDto modifyUserDto);

    /**
     * 添加购票人
     * @param linkUserDto
     * @return
     */
    boolean addLinkUser(@RequestBody LinkUserDto linkUserDto);

    /**
     * 根据省份证查询
     * @param linkUserDto
     * @return
     */
    boolean queryLinkUserByIdCard(@RequestBody LinkUserDto linkUserDto);

    /**
     * 查询购票人 根据userid
     * @param linkUserDto
     * @return
     */
    List<LinkUserVo> queryLinkUser(@RequestBody LinkUserDto linkUserDto);

    /**
     * 删除购票人
     * @param linkUserDto
     * @return
     */
    boolean delLinkUser(@RequestBody LinkUserDto linkUserDto);

    /**
     * 添加评论
     * @param comCommentDto
     * @return
     */
    boolean commitItemComment(ComCommentDto comCommentDto);
}
