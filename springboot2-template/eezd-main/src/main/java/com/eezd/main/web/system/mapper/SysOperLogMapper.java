package com.eezd.main.web.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eezd.common.domain.entity.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    // 根据SysOperLog实体参数去查询结果, 支持多参数查询
    default List<SysOperLog> selectOperLogList(SysOperLog condition) {
        return selectList(Wrappers.lambdaQuery(condition));
    }
}
