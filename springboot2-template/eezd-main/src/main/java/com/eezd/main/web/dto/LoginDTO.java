package com.eezd.main.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录对象
 */
@ApiModel(value = "用户登录对象")
@Data
public class LoginDTO implements Serializable {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "Admin")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", example = "admin123")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String code;

    /**
     * 唯一标识
     */
    @ApiModelProperty("唯一标识")
    private String uuid;
}