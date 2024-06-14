package com.eezd.main.web.system.service;

import com.eezd.common.domain.entity.SysRole;

import java.util.List;

public interface ISysRoleService {
    /**
     * 查询角色
     */
    public List<SysRole> selectRoleList(SysRole role);

    /**
     * 新增角色参数
     */
    public int insertRole(SysRole role);

    /**
     * 修改角色参数, 根据ID
     */
    public int updateRoleById(SysRole role);

    /**
     * 删除角色
     */
    public void deleteRoleByIds(Long[] roleIds);

    /**
     * 校验角色名称是否唯一
     */
    public boolean checkRoleNameUnique(SysRole role);

    /**
     * 校验角色权限是否唯一
     */
    public boolean checkRoleKeyUnique(SysRole role);


}
