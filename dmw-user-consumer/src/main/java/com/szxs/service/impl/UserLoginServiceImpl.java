package com.szxs.service.impl;
import com.szxs.dto.ComCommentDto;
import com.szxs.dto.*;
import com.szxs.entity.DmLinkUser;
import com.szxs.entity.DmUser;
import com.szxs.exception.DmwException;
import com.szxs.exception.LoginException;
import com.szxs.feign.UserLoginClient;
import com.szxs.service.UserLoginService;
import com.szxs.util.*;
import com.szxs.vo.DmUserVo;
import com.szxs.vo.LinkUserVo;
import com.szxs.vo.RespTokenLoginVo;
import com.szxs.vo.UserVoById;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginClient userLoginClient;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录功能
     * @param dmUser
     * @return
     */
    public List<Object> Login(DmUserDto dmUser) {
        List<Object> loginVo = new ArrayList<Object>();
        RespTokenLoginVo tokenLoginVo = new RespTokenLoginVo();
        DmUserVo userVo = new DmUserVo();

        if (EmptyUtil.isNotEmpty(dmUser)){
            //查询
            DmUser user = userLoginClient.queryUser(dmUser);
            BeanUtils.copyProperties(user,userVo);
            //密码加密
            user.setPassword(MD5.getMd5(dmUser.getPassword(),32));

            //设置token类
            tokenLoginVo.setGenTime(new Date());
            tokenLoginVo.setExpTime(DmwConstant.User.TOKEN_EXPTIME_TIME_SECOND);
            String token = MD5.generateToken(user);
            tokenLoginVo.setToken(token);

            loginVo.add(userVo);
            loginVo.add(tokenLoginVo);

            if (EmptyUtil.isEmpty(user)){
                throw new LoginException("没有此用户");
            }

            //判断是否已经登录
            String existsToken = redisUtil.get(DmwConstant.User.TOKEN_PRIFIX + user.getId());
            if (EmptyUtil.isNotEmpty(existsToken)){
                //删除原来的令牌
                redisUtil.hdel(token);
            }

            //键 user：id token
            //用户信息存一分到redis token：用户信息

            redisUtil.set(DmwConstant.User.TOKEN_PRIFIX+user.getId(),token,DmwConstant.User.TOKEN_EXPIRE_TIME);
            redisUtil.hset(token,"id",user.getId().toString());
            redisUtil.hset(token,"phone",user.getPhone());

        }
        return loginVo;
    }

    /**
     * 注册功能
     * @param registerDto
     * @return
     */
    public boolean register(RegisterDto registerDto) {

        DmUser code = userLoginClient.code(registerDto);

        if (EmptyUtil.isEmpty(code)) {
            RegisterDto register = new RegisterDto();
            BeanUtils.copyProperties(registerDto, register);
            register.setPassword(MD5.getMd5(register.getPassword(), 32));
            String vcode = redisUtil.get(registerDto.getPhone());
            if (registerDto.getVcode().equals(vcode)) {
                userLoginClient.register(register);
                return true;
            } else {
                throw new DmwException("验证码错误！");
            }

        }else{
            throw new DmwException("用户已存在！不能注册");
        }
    }

    /**
     * 验证码
     * @param registerDto
     * @return
     */
    public boolean code(RegisterDto registerDto) {
        DmUser code = userLoginClient.code(registerDto);
        if (EmptyUtil.isEmpty(code)) {
            int aa = (int) ((Math.random() * 9 + 1) * 100000);
            if (redisUtil.getTTL(registerDto.getPhone()) > 0) {
                throw new DmwException("验证码已发送请勿重复点击！");
            } else {
                MailUtil.sendMail(registerDto.getPhone(), aa + "");
            }
            registerDto.setVcode(aa + "");
            redisUtil.set(registerDto.getPhone(), aa + "", 60);
            return true;
        }else {
            throw new DmwException("用户已存在！不能注册");
        }
    }

    /**
     * 根据id查用户信息
     * @param id
     * @return
     */
    public UserVoById queryById(Integer id) {
        UserVoById userVoById = userLoginClient.queryById(id);
        userVoById.setImgUrl("calendar05.png");
        return userVoById;
    }

    public boolean updateUser(ModifyUserDto modifyUserDto) {
        modifyUserDto.setImgUrl("calendar05.png");
        return userLoginClient.updateUser(modifyUserDto);
    }

    public boolean commitItemComment(ComCommentDto comCommentDto) {
        comCommentDto.setContent(comCommentDto.getComment());
        return userLoginClient.commitItemComment(comCommentDto);
    }

    public boolean addLinkUser(LinkUserDto linkUserDto) {
        return userLoginClient.addLinkUser(linkUserDto);
    }

    public boolean queryLinkUserByIdCard(LinkUserDto linkUserDto) {
        DmLinkUser dmLinkUser = userLoginClient.queryLinkUserByIdCard(linkUserDto);
        if (EmptyUtil.isEmpty(dmLinkUser)){
            return true;
        }
        return false;
    }

    public List<LinkUserVo> queryLinkUser(LinkUserDto linkUserDto) {
        List<LinkUserVo> linkUserVos = userLoginClient.queryLinkUser(linkUserDto);
        return linkUserVos;
    }

    public boolean delLinkUser(LinkUserDto linkUserDto) {
        return userLoginClient.delLinkUser(linkUserDto);
    }

}
