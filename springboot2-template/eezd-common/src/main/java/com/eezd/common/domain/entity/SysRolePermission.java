package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.eezd.common.constant.ValidationConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SysRolePermission implements Serializable {

    @ApiModelProperty(value = "角色ID", position = 1)
    @NotNull(message = ValidationConstant.NOT_EMPTY_MSG)
    @TableId
    private Long roleId;

    @ApiModelProperty(value = "权限ID", position = 2)
    @NotNull(message = ValidationConstant.NOT_EMPTY_MSG)
    private Long premissionId;
}
