package com.eezd.main.web.dto;

import com.eezd.common.constant.ValidationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = ValidationConstant.NOT_EMPTY_MSG)
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", example = "admin123")
    @NotEmpty(message = ValidationConstant.NOT_EMPTY_MSG)
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private String uuid;
}