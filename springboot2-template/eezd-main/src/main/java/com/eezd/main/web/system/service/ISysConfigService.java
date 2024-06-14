package com.eezd.main.web.system.service;


import com.eezd.common.domain.entity.SysConfig;

import java.util.List;

/**
 * 参数配置 服务层
 */
public interface ISysConfigService {
    /**
     * 查询参数配置信息
     */
    public SysConfig selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     */
    public String selectConfigByKey(String configKey);

    /**
     * 获取验证码开关
     */
    public boolean selectCaptchaEnabled();

    /**
     * 查询参数配置列表
     */
    public List<SysConfig> selectConfigList(SysConfig config);

    /**
     * 新增参数配置
     */
    public int insertConfig(SysConfig config);

    /**
     * 修改参数配置
     */
    public int updateConfig(SysConfig config);

    /**
     * 批量删除参数信息
     */
    public void deleteConfigByIds(Long[] configIds);

    /**
     * 加载参数缓存数据
     */
    public void loadingConfigCache();

    /**
     * 清空参数缓存数据
     */
    public void clearConfigCache();

    /**
     * 重置参数缓存数据
     */
    public void resetConfigCache();

    /**
     * 校验参数键名是否唯一
     */
    public boolean checkConfigKeyUnique(SysConfig config);
}
