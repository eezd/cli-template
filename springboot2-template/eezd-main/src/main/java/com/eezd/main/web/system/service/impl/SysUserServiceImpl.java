package com.eezd.main.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eezd.common.constant.UserConstants;
import com.eezd.common.domain.entity.SysUser;
import com.eezd.common.utils.StringUtils;
import com.eezd.main.web.system.mapper.SysUserMapper;
import com.eezd.main.web.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户 业务层处理
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean checkUserNameUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        // SysUser info = userMapper.checkUserNameUnique(user.getUserName());
        SysUser info = userMapper.selectOne(
                new QueryWrapper<SysUser>()
                        .eq("user_name", user.getUserName())
        );
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean registerUser(SysUser user) {
        return userMapper.insert(user) > 0;
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user) {
        return userMapper.updateById(user);
    }
}
