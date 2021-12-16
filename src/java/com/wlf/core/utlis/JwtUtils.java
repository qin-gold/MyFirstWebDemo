package com.wlf.core.utlis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * jwt 快速工具包
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 23:41
 */
public class JwtUtils {
    private static final Properties load;

    static {
        load = PropertiesLoadUtils.load("config.properties");
    }

    /** 创建Token
     * @param id 用户id
     * @return token
     */
    public static String create(String id) {
        return JWT.create()
                .setPayload("id", id)
                .setIssuedAt(DateUtil.date())
                .setExpiresAt(DateUtil.tomorrow())
                .setSigner(initSigner())
                .sign();
    }

    private static JWTSigner initSigner() {
        return JWTSignerUtil.hs256(load.getProperty("jwtKey").getBytes(StandardCharsets.UTF_8));
    }

    /** 检查加签是否失效
     * @param token token
     * @return boolean
     */
    private static boolean check(String token){
        try {
            JWTValidator
                    .of(token)
                    .validateAlgorithm(JWTSignerUtil.hs256(load.getProperty("jwtKey")
                            .getBytes(StandardCharsets.UTF_8)))
                    .validateDate(DateUtil.date(),0);
        }catch (ValidateException e){
            return false;
        }
        return true;
    }

    /**
     * 获取加签的值
     * @param token token
     * @return 用户id
     */
    public static String getValue(String token){
        if (check(token))
        return JWT.of(token).getPayload("id").toString();
        return "";
    }

    public static String getValue(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
           if ("token".equals(cookie.getName())){
               return getValue(cookie.getValue());
           }
        }
        return null;
    }
}
