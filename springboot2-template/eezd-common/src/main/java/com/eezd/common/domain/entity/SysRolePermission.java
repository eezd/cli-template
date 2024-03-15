package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRolePermission implements Serializable {
    /**
     * 角色ID
     */
    @TableId
    private Long roleId;

    /**
     * 权限ID
     */
    private Long premissionId;

    // @TableField(exist = false)
    // private SysPermission sysPermission;
}
