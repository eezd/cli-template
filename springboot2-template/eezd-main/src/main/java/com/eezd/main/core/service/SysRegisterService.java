package com.eezd.main.core.service;

import com.eezd.common.constant.CacheConstants;
import com.eezd.common.constant.Constants;
import com.eezd.common.constant.UserConstants;
import com.eezd.common.domain.entity.SysUser;
import com.eezd.common.exception.ServiceException;
import com.eezd.common.utils.MessageUtils;
import com.eezd.common.utils.RedisCache;
import com.eezd.common.utils.SecurityUtils;
import com.eezd.common.utils.StringUtils;
import com.eezd.main.core.manager.AsyncManager;
import com.eezd.main.core.manager.factory.AsyncFactory;
import com.eezd.main.web.model.RegisterBody;
import com.eezd.main.web.system.service.ISysConfigService;
import com.eezd.main.web.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注册校验方法
 */
@Component
public class SysRegisterService {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     * msg: 这是最基础的注册功能, 只需要用户名和密码
     *
     * @param registerBody
     * @return
     */
    public String register(RegisterBody registerBody) {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);

        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (!userService.checkUserNameUnique(sysUser)) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        } else {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            if (StringUtils.isNull(sysUser.getRoleId())) {
                sysUser.setRoleId(2L);
            }
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER,
                        MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new ServiceException(MessageUtils.message("user.jcaptcha.expire"));
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new ServiceException(MessageUtils.message("user.jcaptcha.error"));
        }
    }
}
