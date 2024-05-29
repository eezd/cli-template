package com.eezd.main.web.system.controller;

import com.eezd.common.annotation.Log;
import com.eezd.common.domain.AjaxResult;
import com.eezd.common.domain.entity.SysUser;
import com.eezd.common.enums.BusinessType;
import com.eezd.common.utils.SecurityUtils;
import com.eezd.common.utils.StringUtils;
import com.eezd.main.web.dto.SysUserAddDTO;
import com.eezd.main.web.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户信息")
@RestController
@RequestMapping("/system/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/query")
    public AjaxResult query(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        return AjaxResult.success(list);
    }

    /**
     * 新增用户
     */
    @PreAuthorize("hasAuthority('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUserAddDTO userDTO) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDTO, user);


        if (!userService.checkUserNameUnique(user)) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user)) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user)) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }


        // user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        // return AjaxResult.toAjax(userService.insertUser(user));
        return AjaxResult.success(userDTO);
    }

    /**
     * 修改用户
     */
    @PreAuthorize("hasAuthority('system:user:update')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        if (!userService.checkUserNameUnique(user)) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user)) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user)) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        // user.setUpdateBy(getUsername());
        return AjaxResult.toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("hasAuthority('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        if (ArrayUtils.contains(userIds, SecurityUtils.getLoginUser().getUserId())) {
            return AjaxResult.error("当前用户不能删除");
        }
        return AjaxResult.toAjax(userService.deleteUserByIds(userIds));
    }
}
