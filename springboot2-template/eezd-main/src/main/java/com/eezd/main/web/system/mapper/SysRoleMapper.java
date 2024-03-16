package com.eezd.main.web.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eezd.common.domain.entity.SysRole;
import com.eezd.main.web.system.vo.RolePermissionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    // 根据角色ID查询其所有权限信息
    public RolePermissionVO selectRolePermission(Long role_id);
}
