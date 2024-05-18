package com.eezd.main.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eezd.common.domain.LoginUser;
import com.eezd.common.domain.entity.SysPermission;
import com.eezd.common.domain.entity.SysUser;
import com.eezd.common.enums.UserStatus;
import com.eezd.common.exception.ServiceException;
import com.eezd.common.utils.MessageUtils;
import com.eezd.common.utils.StringUtils;
import com.eezd.main.web.system.mapper.SysPermissionMapper;
import com.eezd.main.web.system.mapper.SysRoleMapper;
import com.eezd.main.web.system.mapper.SysUserMapper;
import com.eezd.main.web.system.vo.RolePermissionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户验证处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库中对应的用户名称
        QueryWrapper<SysUser> user_queryWrapper = new QueryWrapper<>();
        user_queryWrapper.eq("user_name", username);
        SysUser user = sysUserMapper.selectOne(user_queryWrapper);

        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException(MessageUtils.message("user.blocked"));
        }

        // 传入 user 进行密码验证
        passwordService.validate(user);

        // 添加权限
        List<SimpleGrantedAuthority> permissions = new ArrayList<>();
        RolePermissionVO userRoleQuery = sysRoleMapper.selectRolePermission(user.getUserId());
        if (user.getUserId() == 1L) {
            userRoleQuery.setSysPermission(sysPermissionMapper.selectList(null));
        }

        // 添加角色
        permissions.add(new SimpleGrantedAuthority("ROLE_".concat(userRoleQuery.getRoleKey())));
        List<SysPermission> userPermissions = userRoleQuery.getSysPermission();
        if (userPermissions != null && !userPermissions.isEmpty()) {
            for (SysPermission permission : userPermissions) {
                permissions.add(new SimpleGrantedAuthority(permission.getPerms()));
            }
        }

        return createLoginUser(user, permissions);
    }

    public UserDetails createLoginUser(SysUser user, List<SimpleGrantedAuthority> permissions) {
        return new LoginUser(user.getUserId(), user, permissions);
    }
}
