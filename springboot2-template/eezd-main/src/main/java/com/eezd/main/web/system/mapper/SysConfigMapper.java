package com.eezd.main.web.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eezd.common.domain.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {
    default SysConfig selectConfig(SysConfig condition) {
        return selectOne(Wrappers.lambdaQuery(condition));
    }
}
