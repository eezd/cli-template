package com.eezd.main.core.service;

import com.eezd.common.constant.CacheConstants;
import com.eezd.common.constant.Constants;
import com.eezd.common.constant.UserConstants;
import com.eezd.common.domain.LoginUser;
import com.eezd.common.domain.entity.SysUser;
import com.eezd.common.exception.ServiceException;
import com.eezd.common.utils.DateUtils;
import com.eezd.common.utils.MessageUtils;
import com.eezd.common.utils.RedisCache;
import com.eezd.common.utils.StringUtils;
import com.eezd.common.utils.ip.IpUtils;
import com.eezd.main.core.manager.AsyncManager;
import com.eezd.main.core.manager.factory.AsyncFactory;
import com.eezd.main.web.system.service.ISysConfigService;
import com.eezd.main.web.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private ISysUserService sysUserService;


    public String login(String username, String password, String code, String uuid) {
        // 验证码校验
        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);
        // 用户验证
        Authentication authentication = null;
        try {
            // 封装请求信息为 Authentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            // 设置联系上下文, 后续方便SysPasswordService获取信息
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new ServiceException(MessageUtils.message("user.password.not.match"));
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            SecurityContextHolder.clearContext();
        }

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());

        // 生成token (token+user数据会一同保存在redis里)
        return tokenService.createToken(loginUser);
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
        boolean captchaEnabled = sysConfigService.selectCaptchaEnabled();
        if (captchaEnabled) {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            redisCache.deleteObject(verifyKey);
            if (captcha == null) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new ServiceException(MessageUtils.message("user.jcaptcha.expire"));
            }
            if (!code.equalsIgnoreCase(captcha)) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new ServiceException(MessageUtils.message("user.jcaptcha.error"));
            }
        }
    }

    /**
     * 登录前置校验
     *
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException(MessageUtils.message("user.password.not.match"));
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException(MessageUtils.message("user.password.not.match"));
        }
        // IP黑名单校验
        String blackStr = sysConfigService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            throw new ServiceException(MessageUtils.message("login.blocked"));
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr());
        sysUser.setLoginDate(DateUtils.getNowDate());
        sysUserService.updateUserProfile(sysUser);
    }
}