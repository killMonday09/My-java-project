package com.szxs.controller;

import com.szxs.dto.ComCommentDto;
import com.szxs.dto.DmUserDto;
import com.szxs.dto.LinkUserDto;
import com.szxs.dto.ModifyUserDto;
import com.szxs.dto.RegisterDto;
import com.szxs.entity.DmLinkUser;
import com.szxs.entity.DmUser;
import com.szxs.mapper.LinkUserMapper;
import com.szxs.mapper.UserMapper;
import com.szxs.vo.LinkUserVo;
import com.szxs.vo.UserVoById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RespLoginUserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LinkUserMapper linkUserMapper;

    @RequestMapping("/queryUser")
    public DmUser queryUser(@RequestBody DmUserDto dmUser){
        return userMapper.queryUser(dmUser);
    }


    @RequestMapping("/register")
    public boolean register(@RequestBody RegisterDto registerDto ){
        return userMapper.saveUser(registerDto) > 0;
    }

    @RequestMapping("/code")
    public DmUser code(@RequestBody RegisterDto registerDto){
        return userMapper.queryUserByPhone(registerDto.getPhone());
    }

    @RequestMapping("/queryById")
    public UserVoById queryById(@RequestParam("id") Integer id){
        return userMapper.queryUserById(id);
    }

    @RequestMapping("/updateUser")
    public boolean updateUser(@RequestBody ModifyUserDto modifyUserDto){
        return userMapper.updateUser(modifyUserDto) > 0;
    }

    @RequestMapping("/commitItemComment")
    public boolean commitItemComment(@RequestBody ComCommentDto comCommentDto){
        return userMapper.commitItemComment(comCommentDto) > 0;
    }

    @RequestMapping("/addLinkUser")
    public boolean addLinkUser(@RequestBody LinkUserDto linkUserDto){
        return linkUserMapper.addLinkUser(linkUserDto) > 0;
    }

    @RequestMapping("/queryLinkUserByIdCard")
    public DmLinkUser queryLinkUserByIdCard(@RequestBody LinkUserDto linkUserDto){
        return linkUserMapper.queryLinkUserByIdCard(linkUserDto);
    }

    @RequestMapping("/queryLinkUser")
    public List<LinkUserVo> queryLinkUser(@RequestBody LinkUserDto linkUserDto){
        return linkUserMapper.queryLinkUser(linkUserDto);
    }

    @RequestMapping("/delLinkUser")
    public boolean delLinkUser(@RequestBody LinkUserDto linkUserDto){
        return linkUserMapper.deleteLinkUserById(linkUserDto) > 0;
    }


    @RequestMapping("/getLinkUser")
    public DmLinkUser getLinkUser(@RequestParam("id") Integer id){
        return linkUserMapper.getLinkUser(id);
    }

}

