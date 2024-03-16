package com.eezd.main.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册对象
 */
@ApiModel(value = "注册登录对象")
@Data
public class RegisterBody extends LoginBody {
}