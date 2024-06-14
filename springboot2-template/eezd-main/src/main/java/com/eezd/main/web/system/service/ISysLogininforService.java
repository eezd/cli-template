package com.eezd.main.web.system.service;

import com.eezd.common.domain.entity.SysLogininfor;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 */
public interface ISysLogininforService {
    /**
     * 新增系统登录日志
     */
    public void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     */
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     */
    public int deleteLogininforByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     */
    public void cleanLogininfor();
}
