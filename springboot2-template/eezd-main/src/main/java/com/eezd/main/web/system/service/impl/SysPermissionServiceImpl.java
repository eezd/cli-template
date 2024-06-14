package com.eezd.main.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eezd.common.constant.UserConstants;
import com.eezd.common.domain.entity.SysPermission;
import com.eezd.common.utils.StringUtils;
import com.eezd.main.web.system.mapper.SysPermissionMapper;
import com.eezd.main.web.system.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public List<SysPermission> selectPermissionList(SysPermission permission) {
        return permissionMapper.selectPermissionList(permission);
    }

    @Override
    public int insertPermission(SysPermission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public int updatePermissionById(SysPermission permission) {
        return permissionMapper.updateById(permission);
    }

    @Override
    public void deletePermissionByIds(Long[] permissionIds) {
        permissionMapper.deleteBatchIds(Arrays.asList(permissionIds));
    }

    @Override
    public boolean checkPermissionNameUnique(SysPermission permission) {
        Long permissionId = StringUtils.isNull(permission.getPremissionId()) ? -1L : permission.getPremissionId();
        // SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        SysPermission info = permissionMapper.selectOne(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getPremissionName, permission.getPremissionName()));
        if (StringUtils.isNotNull(info) && info.getPremissionId().longValue() != permissionId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkPermissionKeyUnique(SysPermission permission) {
        Long permissionId = StringUtils.isNull(permission.getPremissionId()) ? -1L : permission.getPremissionId();
        // SysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        SysPermission info = permissionMapper.selectOne(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getPerms, permission.getPerms()));
        if (StringUtils.isNotNull(info) && info.getPremissionId().longValue() != permissionId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

}
