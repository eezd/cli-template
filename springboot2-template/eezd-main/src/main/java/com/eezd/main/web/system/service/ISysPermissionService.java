package com.eezd.main.web.system.service;

import com.eezd.common.domain.entity.SysPermission;

import java.util.List;

public interface ISysPermissionService {
    /**
     * 查询权限
     */
    public List<SysPermission> selectPermissionList(SysPermission permission);

    /**
     * 新增权限
     */
    public int insertPermission(SysPermission permission);

    /**
     * 修改权限, 根据ID
     */
    public int updatePermissionById(SysPermission permission);

    /**
     * 删除权限
     */
    public void deletePermissionByIds(Long[] permissionIds);

    /**
     * 校验权限名称是否唯一
     */
    public boolean checkPermissionNameUnique(SysPermission permission);

    /**
     * 校验权限值是否唯一
     */
    public boolean checkPermissionKeyUnique(SysPermission permission);
}
