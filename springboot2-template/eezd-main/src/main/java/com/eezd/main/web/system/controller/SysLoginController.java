package com.eezd.main.web.system.controller;

import com.eezd.common.config.eezdConfig;
import com.eezd.common.constant.CacheConstants;
import com.eezd.common.constant.Constants;
import com.eezd.common.domain.AjaxResult;
import com.eezd.common.utils.RedisCache;
import com.eezd.common.utils.sign.Base64;
import com.eezd.common.utils.uuid.IdUtils;
import com.eezd.main.core.service.SysLoginService;
import com.eezd.main.core.service.TokenService;
import com.eezd.main.web.system.controller.model.LoginBody;
import com.eezd.main.web.system.mapper.SysUserMapper;
import com.eezd.main.web.system.service.ISysConfigService;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;


/**
 * 登录验证
 */
@Api(tags = "登录")
@RestController
public class SysLoginController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();

        // 生成令牌
        String token = loginService.login(
                loginBody.getUsername(),
                loginBody.getPassword(),
                loginBody.getCode(),
                loginBody.getUuid()
        );
        ajax.put(Constants.TOKEN, token);

        return ajax;
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")
    public AjaxResult admin() {
        AjaxResult ajax = AjaxResult.success("admin");
        return ajax;
    }

    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/system/user/query")
    public AjaxResult systemUserQuery() {
        AjaxResult ajax = AjaxResult.success("/system/user/query");
        return ajax;
    }

    @GetMapping("/user")
    public AjaxResult user() {
        AjaxResult ajax = AjaxResult.success("user");
        // 获取当前认证的Authentication对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 判断当前认证是否有效
        if (authentication != null && authentication.isAuthenticated()) {
            // 获取用户的所有角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // 打印用户的所有角色
            System.out.println("User Roles: " + authorities);

            // 如果你需要逐个打印角色
            for (GrantedAuthority authority : authorities) {
                System.out.println("Role: " + authority.getAuthority());
            }
        } else {
            System.out.println("Authentication is null or not authenticated.");
        }


        return ajax;
    }


    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaEnabled = sysConfigService.selectCaptchaEnabled();
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = eezdConfig.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }
}
