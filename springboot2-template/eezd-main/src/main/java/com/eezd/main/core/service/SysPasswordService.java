package com.eezd.main.core.service;

import com.eezd.common.constant.CacheConstants;
import com.eezd.common.constant.Constants;
import com.eezd.common.domain.entity.SysUser;
import com.eezd.common.exception.ServiceException;
import com.eezd.common.utils.MessageUtils;
import com.eezd.common.utils.RedisCache;
import com.eezd.common.utils.SecurityUtils;
import com.eezd.main.core.manager.AsyncManager;
import com.eezd.main.core.manager.factory.AsyncFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 登录密码验证
 */
@Component
public class SysPasswordService {
    @Autowired
    private RedisCache redisCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username) {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    /**
     * 验证密码是否正确
     *
     * @param user
     */
    public void validate(SysUser user) {
        // 获取联系上下文 user
        Authentication usernamePasswordAuthenticationToken = SecurityContextHolder.getContext().getAuthentication();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();

        // 从缓存中获取密码重试次数
        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));

        if (retryCount == null) {
            retryCount = 0;
        }
        // 超过最大重试次数, 抛出异常
        if (retryCount >= Integer.valueOf(maxRetryCount).intValue()) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount, lockTime)));
            throw new ServiceException(MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount, lockTime));
        }

        // 检查密码是否正确
        // Authentication联系上下文的user.password 与 参数的 user.password 比较)
        if (!matches(user, password)) {
            retryCount = retryCount + 1;
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.retry.limit.count", retryCount)));
            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw new ServiceException(MessageUtils.message("user.password.not.match"));
        } else {
            clearLoginRecordCache(username);
        }
    }

    /**
     * 检查user中加密后的密码是否与第二参数的明文密码是否相同
     *
     * @param user
     * @param rawPassword
     * @return
     */
    public boolean matches(SysUser user, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName) {
        if (redisCache.hasKey(getCacheKey(loginName))) {
            redisCache.deleteObject(getCacheKey(loginName));
        }
    }
}
