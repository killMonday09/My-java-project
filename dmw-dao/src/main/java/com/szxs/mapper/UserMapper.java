package com.szxs.mapper;

import com.szxs.dto.ComCommentDto;
import com.szxs.dto.DmUserDto;
import com.szxs.dto.ModifyUserDto;
import com.szxs.dto.RegisterDto;
import com.szxs.entity.DmUser;
import com.szxs.vo.DmUserVo;
import com.szxs.vo.UserVoById;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询单个用户
     * @param dmUser
     * @return
     */
    DmUser queryUser(@RequestBody DmUserDto dmUser);

    /**
     * 根据phone查用户是否已经存在
     * @param phone
     * @return
     */
    DmUser queryUserByPhone(String phone);

    /**
     * 保存用户
     * @param registerDto
     * @return
     */
    int saveUser(@RequestBody RegisterDto registerDto );

    /**
     * 根据phone查用户是否已经存在
     * @param id
     * @return
     */
    UserVoById queryUserById(Integer id);

    /**
     * 修改用户信息
     * @param modifyUserDto
     * @return
     */
    int updateUser(ModifyUserDto modifyUserDto);

    /**
     * 添加评论
     * @param comCommentDto
     * @return
     */
    int commitItemComment(ComCommentDto comCommentDto);
}
