package com.eezd.main.web.system.controller;

import com.eezd.common.constant.Constants;
import com.eezd.common.domain.AjaxResult;
import com.eezd.common.domain.ValidationGroup;
import com.eezd.common.utils.StringUtils;
import com.eezd.main.core.service.SysLoginService;
import com.eezd.main.core.service.SysRegisterService;
import com.eezd.main.core.service.TokenService;
import com.eezd.main.web.dto.LoginDTO;
import com.eezd.main.web.dto.RegisterDTO;
import com.eezd.main.web.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;


/**
 * 登录验证
 */
@Api(tags = "登录注册")
@RestController
public class SysLoginRegisterController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;


    /**
     * 登录方法
     *
     * @param loginDTO 登录信息
     * @return 结果
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDTO loginDTO) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(
                loginDTO.getUsername(),
                loginDTO.getPassword(),
                loginDTO.getCode(),
                loginDTO.getUuid()
        );
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public AjaxResult register(
            @Validated({ValidationGroup.AddGroup.class, Default.class})
            @RequestBody RegisterDTO user
    ) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return AjaxResult.error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? AjaxResult.success() : AjaxResult.error(msg);
    }

}
