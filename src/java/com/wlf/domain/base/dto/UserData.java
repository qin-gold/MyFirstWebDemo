package com.wlf.domain.base.dto;

import com.wlf.domain.base.Account;
import com.wlf.domain.base.LoginStatus;
import com.wlf.domain.base.User;
import com.wlf.domain.base.permission.Menu;
import com.wlf.domain.base.permission.Role;
import lombok.Data;

import java.util.List;

/**
 * 用户登录系统获取的数据的实体
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 17:36
 */
@Data
public class UserData {
    private User user;
    private List<Role> roles;
    private List<Menu> menus;
    private Account account;
    private LoginStatus loginStatus;
}
