package com.eezd.main.web.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eezd.common.domain.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    // 根据SysConfig实体参数去查询结果, 支持多参数查询
    default SysConfig selectConfigOne(SysConfig config) {
        return selectOne(Wrappers.lambdaQuery(config));
    }

    default List<SysConfig> selectConfigList(SysConfig config) {
        return selectList(Wrappers.lambdaQuery(config));
    }
}
