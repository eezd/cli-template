package com.eezd.main.web.model;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录对象
 */
@ApiModel(value = "用户登录对象")
@Data
public class LoginBody implements Serializable {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "Admin")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", example = "Admin123")
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