package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.eezd.common.constant.ValidationConstant;
import com.eezd.common.domain.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysRole implements Serializable {

    @ApiModelProperty(value = "角色ID", position = 1)
    @NotNull(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.UpdateGroup.class})
    @TableId(type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty(value = "角色名称", position = 2, example = "测试角色")
    @NotBlank(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String roleName;

    @ApiModelProperty(value = "角色权限", position = 3, example = "test")
    @NotBlank(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String roleKey;

    @ApiModelProperty(value = "角色排序", position = 4, example = "3")
    @Range(min = 0, max = 9999, message = ValidationConstant.ROLE_SORT_MSG)
    private Integer roleSort;

    @ApiModelProperty(value = "角色状态(0正常 1停用)", position = 5, allowableValues = "0,1", example = "0")
    @Range(min = 0, max = 1, message = ValidationConstant.ROLE_STATUS_MSG)
    private String status;

    @ApiModelProperty(value = "删除标志(0代表存在 1代表删除)", position = 6, allowableValues = "0,1", example = "0")
    @Range(min = 0, max = 1, message = ValidationConstant.DEL_FLAG_MSG)
    private String delFlag;

    @ApiModelProperty(value = "创建人", hidden = true, position = 7)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间", hidden = true, position = 8)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新人", hidden = true, position = 9)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间", hidden = true, position = 10)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "备注", position = 11)
    private String remark;
}
