package com.szxs.vo;

import com.szxs.entity.DmUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {
    private List<DmUser> user;
    private List<RespTokenLoginVo> token;
}
