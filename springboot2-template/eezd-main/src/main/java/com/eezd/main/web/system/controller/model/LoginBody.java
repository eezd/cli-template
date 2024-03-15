package com.eezd.main.web.system.controller.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录对象
 */
@Data
public class LoginBody implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;
}