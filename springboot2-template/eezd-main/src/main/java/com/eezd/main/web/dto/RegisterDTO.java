package com.eezd.main.web.dto;

import com.eezd.common.domain.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户注册对象
 */
@ApiModel(value = "注册登录对象")
@JsonIgnoreProperties(value = {"userId", "roleId", "nickName", "status", "delFlag"})
@Data
public class RegisterDTO extends SysUser {

    @JsonIgnore
    private Long userId;

    @JsonIgnore
    private Long roleId;

    @JsonIgnore
    private String nickName;

    @JsonIgnore
    private String status;

    @JsonIgnore
    private String delFlag;

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