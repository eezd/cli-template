package com.eezd.main.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "添加用户")
@Data
public class SysUserAddDTO implements Serializable {

    @ApiModelProperty(value = "角色ID", required = true, position = 1, example = "2")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @ApiModelProperty(value = "用户账号", required = true, position = 2, example = "ApiTest")
    @NotNull(message = "userName不能为空")
    private String userName;

    @ApiModelProperty(value = "用户昵称", example = "测试系统用户")
    private String nickName;

    @ApiModelProperty(value = "用户邮箱", required = true, example = "ApiTest@qq.com")
    private String email;

    @ApiModelProperty(value = "手机号码", required = true, example = "15988888888")
    private String phonenumber;

    @ApiModelProperty(value = "用户性别", example = "1")
    private String sex;

    @ApiModelProperty(value = "用户头像", example = "https://qmplusimg.henrongyi.top/gva_header.jpg")
    private String avatar;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）", example = "0")
    private String status;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）", example = "0")
    private String delFlag;

    @ApiModelProperty(value = "备注", example = "测试数据")
    private String remark;

}
