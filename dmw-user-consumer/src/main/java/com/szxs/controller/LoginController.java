package com.szxs.controller;

import com.alibaba.fastjson.JSON;
import com.szxs.dto.ComCommentDto;
import com.szxs.dto.DmUserDto;
import com.szxs.dto.LinkUserDto;
import com.szxs.dto.ModifyUserDto;
import com.szxs.dto.RegisterDto;
import com.szxs.entity.DmUser;
import com.szxs.feign.UserLoginClient;
import com.szxs.service.UserLoginService;
import com.szxs.util.RedisUtil;
import com.szxs.util.VoUtil;
import com.szxs.vo.RespDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/p/login")
    public RespDataVo login2(@RequestBody DmUserDto dmUser){
        try {
            RespDataVo success = VoUtil.getSuccess(userLoginService.Login(dmUser));
            success.setMsg("登录成功");
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            RespDataVo failed = VoUtil.getFailed("30001", e.getMessage());
            return failed;
        }
    }

    @PostMapping("/p/register")
    public RespDataVo register(@RequestBody RegisterDto registerDto){
        try {
            RespDataVo success = VoUtil.getSuccess(userLoginService.register(registerDto));
            success.setMsg("注册成功");
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/p/code")
    public RespDataVo code(@RequestBody RegisterDto registerDto){
        try {
            RespDataVo success = VoUtil.getSuccess(userLoginService.code(registerDto));
            success.setMsg("验证码已发送");
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/v/queryUserInfoByToken")
    public RespDataVo queryUserInfoByToken(@RequestHeader String token){
        Map<String, String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        try {
            return VoUtil.getSuccess(userLoginService.queryById(Integer.parseInt(id)));
        }catch (Exception e){
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/v/modifyUserInfo")
    public RespDataVo modifyUserInfo(@RequestBody ModifyUserDto modifyUserDto,@RequestHeader String token){
        Map<String, String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        modifyUserDto.setId(Integer.parseInt(id));
        try {
            return VoUtil.getSuccess(userLoginService.updateUser(modifyUserDto));
        } catch (Exception e) {
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/v/commitItemComment")
    public RespDataVo commitItemComment(@RequestBody ComCommentDto comCommentDto,@RequestHeader String token) {
        Map<String,String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        comCommentDto.setUserId(Integer.parseInt(id));
        try {
            return VoUtil.getSuccess(userLoginService.commitItemComment(comCommentDto));
        } catch (Exception e) {
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/v/queryLinkUserByIdCard")
    public RespDataVo queryLinkUserByIdCard(@RequestBody LinkUserDto linkUserDto, @RequestHeader String token){
        Map<String, String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        linkUserDto.setUserId(id);
        try {
            RespDataVo success = VoUtil.getSuccess(userLoginService.queryLinkUserByIdCard(linkUserDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/v/addLinkUser")
    public RespDataVo addLinkUser(@RequestBody LinkUserDto linkUserDto, @RequestHeader String token){
        Map<String, String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        linkUserDto.setUserId(id);
        try {
            RespDataVo success = VoUtil.getSuccess(userLoginService.addLinkUser(linkUserDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/v/queryLinkUser")
    public RespDataVo queryLinkUser(@RequestBody LinkUserDto linkUserDto, @RequestHeader String token){
        Map<String, String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        linkUserDto.setUserId(id);
        try {
            RespDataVo success = VoUtil.getSuccess(userLoginService.queryLinkUser(linkUserDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

    @PostMapping("/v/deleteLinkUserById")
    public RespDataVo deleteLinkUserById(@RequestBody LinkUserDto linkUserDto){
        try {
            RespDataVo success = VoUtil.getSuccess(userLoginService.delLinkUser(linkUserDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            return VoUtil.getFailed("30001", e.getMessage());
        }
    }

}
