package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.eezd.common.constant.ValidationConstant;
import com.eezd.common.domain.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID", position = 1)
    @NotNull(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.UpdateGroup.class})
    @TableId(type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "角色ID", position = 2, example = "2")
    private Long roleId;

    @ApiModelProperty(value = "用户账号", position = 3, example = "test")
    @Length(min = ValidationConstant.USERNAME_MIN_LENGTH, max = ValidationConstant.USERNAME_MAX_LENGTH, message = ValidationConstant.USERNAME_LENGTH_MSG)
    @NotEmpty(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String userName;

    @ApiModelProperty(value = "用户昵称", position = 4, example = "测试员")
    @Length(min = ValidationConstant.USERNAME_MIN_LENGTH, max = ValidationConstant.USERNAME_MAX_LENGTH, message = ValidationConstant.USERNAME_LENGTH_MSG)
    private String nickName;

    @ApiModelProperty(value = "用户邮箱", position = 5, example = "test@qq.com")
    @Pattern(regexp = ValidationConstant.EMAIL, message = ValidationConstant.EMAIL_MSG)
    @Length(min = ValidationConstant.EMAIL_MIN_LENGTH, max = ValidationConstant.EMAIL_MAX_LENGTH, message = ValidationConstant.EMAIL_LENGTH_MSG)
    @NotEmpty(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String email;

    @ApiModelProperty(value = "手机号码", position = 6, example = "15888888889")
    @Pattern(regexp = ValidationConstant.PHONE, message = ValidationConstant.PHONE_MSG)
    @NotEmpty(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String phonenumber;

    @ApiModelProperty(value = "用户性别(0男 1女 2未知)", position = 7, example = "2")
    @Range(min = 0, max = 2)
    private String sex;

    @ApiModelProperty(value = "用户头像", position = 8, example = "https://qmplusimg.henrongyi.top/gva_header.jpg")
    private String avatar;

    @ApiModelProperty(value = "密码", position = 9, example = "123456")
    // @Pattern(regexp = ValidationConstant.HAS_ONE_UPPERCASE, message = ValidationConstant.HAS_ONE_UPPERCASE_MSG)
    // @Pattern(regexp = ValidationConstant.HAS_ONE_LOWERCASE, message = ValidationConstant.HAS_ONE_LOWERCASE_MSG)
    // @Pattern(regexp = ValidationConstant.HAS_ONE_NUM, message = ValidationConstant.HAS_ONE_NUM_MSG)
    @Length(min = ValidationConstant.PASSWORD_MIN_LENGTH, max = ValidationConstant.PASSWORD_MAX_LENGTH, message = ValidationConstant.PASSWORD_LENGTH_MSG)
    @NotEmpty(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String password;

    @ApiModelProperty(value = "帐号状态(0正常 1停用)", position = 10, example = "0")
    @Range(min = 0, max = 1)
    private String status;

    @ApiModelProperty(value = "删除标志(0代表存在 1代表删除)", position = 11, example = "0")
    @Range(min = 0, max = 1)
    private String delFlag;

    @ApiModelProperty(value = "最后登录IP", hidden = true, position = 12)
    @Null(message = ValidationConstant.EMPTY_MSG)
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间", hidden = true, position = 13)
    @Null(message = ValidationConstant.EMPTY_MSG)
    private Date loginDate;

    @ApiModelProperty(value = "创建人", hidden = true, position = 14)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间", hidden = true, position = 15)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新人", hidden = true, position = 16)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间", hidden = true, position = 17)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "备注", position = 18)
    private String remark;

}