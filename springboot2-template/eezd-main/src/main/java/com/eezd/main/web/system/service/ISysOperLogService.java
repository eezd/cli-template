package com.eezd.main.web.system.service;

import com.eezd.common.domain.entity.SysOperLog;

import java.util.List;

/**
 * 操作日志 服务层
 */
public interface ISysOperLogService {
    /**
     * 新增操作日志
     */
    public void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     */
    public int deleteOperLogByIds(Long[] operIds);

    /**
     * 查询操作日志详细
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
