package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.eezd.common.constant.ValidationConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
public class SysPermission implements Serializable {

    @ApiModelProperty(value = "权限ID", position = 1)
    @TableId(type = IdType.AUTO)
    private Long premissionId;

    @ApiModelProperty(value = "权限名称", position = 2)
    @Null(message = ValidationConstant.NOT_EMPTY_MSG)
    private String premissionName;

    @ApiModelProperty(value = "权限字符串", position = 3)
    @Null(message = ValidationConstant.NOT_EMPTY_MSG)
    private String perms;

    @ApiModelProperty(value = "备注", position = 4)
    private String remark;

}
