package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRolePermission implements Serializable {

    @ApiModelProperty(value = "角色ID", position = 1)
    @TableId
    private Long roleId;

    @ApiModelProperty(value = "权限ID", position = 2)
    private Long premissionId;
}
