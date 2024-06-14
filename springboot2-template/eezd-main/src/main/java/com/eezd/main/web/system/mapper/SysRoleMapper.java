package com.eezd.main.web.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eezd.common.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    default SysRole selectRoleOne(SysRole sysRole) {
        return selectOne(Wrappers.lambdaQuery(sysRole));
    }

    default List<SysRole> selectRoleList(SysRole sysRole) {
        return selectList(Wrappers.lambdaQuery(sysRole));
    }
}
