package com.eezd.main.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eezd.common.domain.entity.SysRolePermission;
import com.eezd.main.web.system.mapper.SysRolePermissionMapper;
import com.eezd.main.web.system.service.ISysRolePermissionService;
import com.eezd.main.web.system.vo.RolePermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;

    @Override
    public int insertRolePermission(SysRolePermission rolePermission) {
        return rolePermissionMapper.insert(rolePermission);
    }

    // @Override
    // public boolean insertRolePermissionList(List<SysRolePermission> rolePermissionList) {
    //     return saveBatch(rolePermissionList);
    // }

    @Override
    public void deleteRolePermission(SysRolePermission rolePermission) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysRolePermission::getRoleId, rolePermission.getRoleId())
                .eq(SysRolePermission::getPremissionId, rolePermission.getPremissionId());
        rolePermissionMapper.delete(queryWrapper);
    }

    // @Override
    // public void deleteRolePermissionList(List<SysRolePermission> rolePermissionList) {
    //     for (SysRolePermission rolePermission : rolePermissionList) {
    //         LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery();
    //         queryWrapper.eq(SysRolePermission::getRoleId, rolePermission.getRoleId())
    //                 .eq(SysRolePermission::getPremissionId, rolePermission.getPremissionId());
    //         rolePermissionMapper.delete(queryWrapper);
    //     }
    // }

    @Override
    public RolePermissionVO selectRolePermission(Long roleId) {
        return rolePermissionMapper.selectRolePermission(roleId);
    }
}
