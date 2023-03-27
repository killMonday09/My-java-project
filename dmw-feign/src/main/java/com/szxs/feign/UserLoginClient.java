package com.szxs.feign;

import com.szxs.dto.ComCommentDto;
import com.szxs.dto.DmUserDto;
import com.szxs.dto.LinkUserDto;
import com.szxs.dto.ModifyUserDto;
import com.szxs.dto.RegisterDto;
import com.szxs.entity.DmLinkUser;
import com.szxs.entity.DmUser;
import com.szxs.vo.LinkUserVo;
import com.szxs.vo.UserVoById;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("dmw-user-provider")
public interface UserLoginClient {
    /**
     * 查询用户
     * @param dmUser
     * @return
     */
    @RequestMapping("/queryUser")
    DmUser queryUser(@RequestBody DmUserDto dmUser);

    /**
     * 注册
     * @param registerDto
     * @return
     */
    @RequestMapping("/register")
    boolean register(@RequestBody RegisterDto registerDto );

    /**
     *
     * @param registerDto
     * @return
     */
    @RequestMapping("/code")
    DmUser code(@RequestBody RegisterDto registerDto);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping("/queryById")
    UserVoById queryById(@RequestParam("id") Integer id);

    /**
     * 修改用户信息
     * @param modifyUserDto
     * @return
     */
    @RequestMapping("/updateUser")
    boolean updateUser(@RequestBody ModifyUserDto modifyUserDto);

    /**
     * 添加购票人
     * @param linkUserDto
     * @return
     */
    @RequestMapping("/addLinkUser")
    public boolean addLinkUser(@RequestBody LinkUserDto linkUserDto);

    /**
     * 根据省份证查询
     * @param linkUserDto
     * @return
     */
    @RequestMapping("/queryLinkUserByIdCard")
    public DmLinkUser queryLinkUserByIdCard(@RequestBody LinkUserDto linkUserDto);

    /**
     * 查询购票人 根据userid
     * @param linkUserDto
     * @return
     */
    @RequestMapping("/queryLinkUser")
    public List<LinkUserVo> queryLinkUser(@RequestBody LinkUserDto linkUserDto);

    /**
     * 删除购票人
     * @param linkUserDto
     * @return
     */
    @RequestMapping("/delLinkUser")
    public boolean delLinkUser(@RequestBody LinkUserDto linkUserDto);

    @RequestMapping("/commitItemComment")
    boolean commitItemComment(@RequestBody ComCommentDto comCommentDto);

    @RequestMapping("/getLinkUser")
    public DmLinkUser getLinkUser(@RequestParam("id") Integer id);

}
