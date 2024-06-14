package com.eezd.main.web.system.controller;

import com.eezd.common.annotation.Log;
import com.eezd.common.domain.AjaxResult;
import com.eezd.common.domain.ValidationGroup;
import com.eezd.common.domain.entity.SysRole;
import com.eezd.common.domain.entity.SysRolePermission;
import com.eezd.common.enums.BusinessType;
import com.eezd.main.web.dto.SysRoleAddDTO;
import com.eezd.main.web.system.service.ISysRolePermissionService;
import com.eezd.main.web.system.service.ISysRoleService;
import com.eezd.main.web.system.vo.RolePermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysRolePermissionService rolePermissionService;

    @ApiOperation("角色查询")
    @PreAuthorize("hasAuthority('system:role:query')")
    @GetMapping("/query")
    public AjaxResult select(SysRole sysRole) {
        List<SysRole> list = roleService.selectRoleList(sysRole);
        return AjaxResult.success(list);
    }

    @ApiOperation("角色添加")
    @PreAuthorize("hasAuthority('system:role:add')")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(
            @Validated({ValidationGroup.AddGroup.class, Default.class})
            @RequestBody SysRoleAddDTO sysRole
    ) {
        if (!roleService.checkRoleNameUnique(sysRole)) {
            return AjaxResult.error("新增角色'" + sysRole.getRoleName() + "'失败，角色名称已存在");
        } else if (!roleService.checkRoleKeyUnique(sysRole)) {
            return AjaxResult.error("新增角色'" + sysRole.getRoleKey() + "'失败，角色权限已存在");
        }
        return AjaxResult.toAjax(roleService.insertRole(sysRole));
    }

    @ApiOperation(value = "角色修改", notes = "根据ID修改")
    @PreAuthorize("hasAuthority('system:role:update')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult update(
            @Validated({ValidationGroup.UpdateGroup.class, Default.class})
            @RequestBody SysRole sysRole
    ) {
        if (sysRole.getRoleId() == 1L) {
            return AjaxResult.error("不允许操作超级管理员角色");
        }
        if (!roleService.checkRoleNameUnique(sysRole)) {
            return AjaxResult.error("修改角色'" + sysRole.getRoleName() + "'失败，角色名称已存在");
        } else if (!roleService.checkRoleKeyUnique(sysRole)) {
            return AjaxResult.error("修改角色'" + sysRole.getRoleKey() + "'失败，角色权限已存在");
        }
        return AjaxResult.toAjax(roleService.updateRoleById(sysRole));
    }

    @ApiOperation(value = "角色删除", notes = "根据ID删除, 可以传递多个")
    @PreAuthorize("hasAuthority('system:role:remove')")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        roleService.deleteRoleByIds(roleIds);
        return AjaxResult.success();
    }

    @ApiOperation(value = "角色权限查询")
    @PreAuthorize("hasAuthority('system:role:query') and hasAuthority('system:permission:query')")
    @GetMapping("/query-permission")
    public AjaxResult selectRolePermissionById(Long roleId) {
        RolePermissionVO list = rolePermissionService.selectRolePermission(roleId);
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "角色权限添加")
    @PreAuthorize("hasAuthority('system:role:add') and hasAuthority('system:permission:add')")
    @Log(title = "角色权限", businessType = BusinessType.INSERT)
    @GetMapping("/add-permission")
    public AjaxResult addRolePermission(@Validated SysRolePermission rolePermission) {
        System.out.println("Permission ID: " + rolePermission.getRoleId());
        System.out.println("Permission ID: " + rolePermission.getPremissionId());
        return AjaxResult.toAjax(rolePermissionService.insertRolePermission(rolePermission));
    }

    @ApiOperation(value = "角色权限删除")
    @PreAuthorize("hasAuthority('system:role:remove') and hasAuthority('system:permission:remove')")
    @Log(title = "角色权限", businessType = BusinessType.DELETE)
    @PostMapping("/remove-permission")
    public AjaxResult removeRolePermission(@Validated SysRolePermission rolePermission) {
        rolePermissionService.deleteRolePermission(rolePermission);
        return AjaxResult.success();
    }
}
