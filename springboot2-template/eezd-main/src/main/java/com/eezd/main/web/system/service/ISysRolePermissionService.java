package com.eezd.main.web.system.service;

import com.eezd.common.domain.entity.SysRolePermission;
import com.eezd.main.web.system.vo.RolePermissionVO;

public interface ISysRolePermissionService {
    /**
     * 新增角色权限
     */
    public int insertRolePermission(SysRolePermission rolePermission);

    /**
     * 批量新增角色权限
     */
    // public boolean insertRolePermissionList(List<SysRolePermission> rolePermissionList);

    /**
     * 删除角色权限
     */
    public void deleteRolePermission(SysRolePermission rolePermission);

    /**
     * 删除角色权限
     */
    // public void deleteRolePermissionList(List<SysRolePermission> rolePermissionList);

    /**
     * 根据角色ID查询其所有权限信息
     */
    public RolePermissionVO selectRolePermission(Long roleId);
}
