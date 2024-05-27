package com.eezd.main.web;

import com.eezd.common.annotation.Anonymous;
import com.eezd.common.annotation.Log;
import com.eezd.common.domain.AjaxResult;
import com.eezd.common.domain.entity.SysConfig;
import com.eezd.common.enums.BusinessType;
import com.eezd.common.utils.RedisCache;
import com.eezd.common.utils.SecurityUtils;
import com.eezd.main.web.system.mapper.SysConfigMapper;
import com.eezd.main.web.system.mapper.SysRoleMapper;
import com.eezd.main.web.system.mapper.SysRolePermissionMapper;
import com.eezd.main.web.system.mapper.SysUserMapper;
import com.eezd.main.web.system.vo.RolePermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "hello测试")
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private RedisCache redisCache;

    @ApiOperation(value = "hello", notes = "hello测试模块")
    @GetMapping("/")
    public AjaxResult hello() {
        RolePermissionVO list = sysRoleMapper.selectRolePermission(2L);

        // SysConfig sysConfig = new SysConfig();
        // sysConfig.setConfigId(3L);
        // sysConfig.setConfigType("Y2");

        return AjaxResult.success(list);
    }

    @Anonymous
    @GetMapping("/test")
    @ApiOperation("test")
    public AjaxResult test() {
        // 生成令牌
        // String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        // ajax.put(Constants.TOKEN, "token123");

        // throw new BaseException(null, "user.not.exists", null, null); // "msg": "用户不存在/密码错误"

        // throw new ServiceException("业务异常", 400);

        // String name = eezdConfig.getName();
        // System.out.println(name);

        // 页码为1, 每页2条记录
        // PageHelper.startPage(1, 2);
        //
        // List<SysUser> user_list = sysUserMapper.selectList(null);
        //
        // // 封装分页结果
        // PageInfo<SysUser> pageInfo = new PageInfo<>(user_list);
        //
        // Map<String, Object> end_data = new HashMap<>();
        //
        // end_data.put("list", user_list);
        // end_data.put("getPages", pageInfo.getTotal());
        //
        // log.info("end_data:{}", end_data);

        // log.info();

        return AjaxResult.success(SecurityUtils.getAuthentication());
    }

    @ApiOperation("测试是否拥有admin角色")
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")
    public AjaxResult admin() {
        AjaxResult ajax = AjaxResult.success("admin");
        return ajax;
    }

    @ApiOperation("测试是否拥有system:user:query权限")
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/system/user/query")
    public AjaxResult systemUserQuery() {
        AjaxResult ajax = AjaxResult.success("/system/user/query");
        return ajax;
    }

    @ApiOperation("xss测试")
    @Anonymous
    @PostMapping("/xss-test")
    public String xssTest(
            @RequestParam(value = "name") String name,
            HttpServletRequest request, HttpServletResponse response, ModelMap m
    ) {
        return "name is:" + name;
    }

    @ApiOperation("redis测试")
    @Anonymous
    @GetMapping("/redis-test")
    public String redisTest() {
        redisCache.setCacheObject("key", "value");
        String name = (String) redisCache.getCacheObject("key");
        return "name is:" + name;
    }

    @ApiOperation("@Log测试")
    @Log(title = "log-test", businessType = BusinessType.OTHER)
    @PostMapping("/log-test")
    public AjaxResult testLog(
            @ApiParam(value = "密码", required = true, example = "123456")
            @RequestParam(value = "psw") String psw
    ) {
        // 设置账号初始密码为1234567
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigId(2L);
        sysConfig.setConfigValue(psw);

        int result = sysConfigMapper.updateById(sysConfig);

        return AjaxResult.success(result);
    }
}