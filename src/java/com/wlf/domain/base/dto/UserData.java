package com.wlf.domain.base.dto;

import com.wlf.domain.base.LoginStatus;
import com.wlf.domain.base.User;
import com.wlf.domain.base.permission.Menu;
import com.wlf.server.base.UserService;
import com.wlf.server.base.impl.UserServiceImpl;
import com.wlf.utlis.JwtUtils;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
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
    private final UserService service = new UserServiceImpl();

    private String id;
    private User user;
    private List<Menu> menus;
    private LoginStatus loginStatus;

    public UserData(String id, HttpServletRequest request) {
        this.id = id;
        this.user=(User) service.findById(id).getData();
//        this.loginStatus = new LoginStatus(id,user.getName(),request.getRemoteAddr(), JwtUtils.create(id),0,"");
//        this.menus =
    }
}
