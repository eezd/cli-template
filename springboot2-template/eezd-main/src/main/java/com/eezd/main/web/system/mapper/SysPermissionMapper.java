package com.eezd.main.web.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eezd.common.domain.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    default SysPermission selectPermissionOne(SysPermission permission) {
        return selectOne(Wrappers.lambdaQuery(permission));
    }

    default List<SysPermission> selectPermissionList(SysPermission permission) {
        return selectList(Wrappers.lambdaQuery(permission));
    }
}
