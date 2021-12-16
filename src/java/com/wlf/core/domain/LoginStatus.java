package com.wlf.core.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 登录控制表
 *
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-27 15:57
 */
@Data
public class LoginStatus {
    private String id;
    private String accountName;
    private String userId;
    private Timestamp loginTime;
    private int status;
    private String loginResult;
    private String ip;
}
