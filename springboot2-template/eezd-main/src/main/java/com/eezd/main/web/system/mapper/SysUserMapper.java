package com.eezd.main.web.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eezd.common.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    default List<SysUser> selectUserList(SysUser config) {
        return selectList(Wrappers.lambdaQuery(config));
    }
}
