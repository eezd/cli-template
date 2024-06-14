package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.eezd.common.constant.ValidationConstant;
import com.eezd.common.domain.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SysPermission implements Serializable {

    @ApiModelProperty(value = "权限ID", position = 1, example = "1100")
    @NotNull(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.UpdateGroup.class})
    @TableId(type = IdType.AUTO)
    private Long premissionId;

    @ApiModelProperty(value = "权限名称", position = 2, example = "权限测试")
    @NotBlank(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String premissionName;

    @ApiModelProperty(value = "权限字符串", position = 3, example = "test:test:add")
    @NotBlank(message = ValidationConstant.NOT_EMPTY_MSG, groups = {ValidationGroup.AddGroup.class})
    private String perms;

    @ApiModelProperty(value = "备注", position = 4)
    private String remark;

}
