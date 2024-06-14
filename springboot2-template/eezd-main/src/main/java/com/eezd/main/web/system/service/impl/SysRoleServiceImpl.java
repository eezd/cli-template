package com.eezd.main.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eezd.common.constant.UserConstants;
import com.eezd.common.domain.entity.SysRole;
import com.eezd.common.utils.StringUtils;
import com.eezd.main.web.system.mapper.SysRoleMapper;
import com.eezd.main.web.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public int insertRole(SysRole role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRoleById(SysRole role) {
        return roleMapper.updateById(role);
    }

    @Override
    public void deleteRoleByIds(Long[] roleIds) {
        roleMapper.deleteBatchIds(Arrays.asList(roleIds));
    }

    @Override
    public boolean checkRoleNameUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        // SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        SysRole info = roleMapper.selectOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName, role.getRoleName()));
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkRoleKeyUnique(SysRole role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        // SysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        SysRole info = roleMapper.selectOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleKey, role.getRoleKey()));
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

}
