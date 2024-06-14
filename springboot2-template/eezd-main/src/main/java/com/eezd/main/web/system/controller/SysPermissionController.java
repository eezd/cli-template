package com.eezd.main.web.system.controller;

import com.eezd.common.annotation.Log;
import com.eezd.common.domain.AjaxResult;
import com.eezd.common.domain.ValidationGroup;
import com.eezd.common.domain.entity.SysPermission;
import com.eezd.common.enums.BusinessType;
import com.eezd.main.web.dto.SysPermissionAddDTO;
import com.eezd.main.web.system.service.ISysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@Api(tags = "权限管理")
@RestController
@RequestMapping("/system/permission")
public class SysPermissionController {

    @Autowired
    private ISysPermissionService permissionService;

    @ApiOperation("权限查询")
    @PreAuthorize("hasAuthority('system:permission:query')")
    @GetMapping("/query")
    public AjaxResult select(SysPermission sysPermission) {
        List<SysPermission> list = permissionService.selectPermissionList(sysPermission);
        return AjaxResult.success(list);
    }

    @ApiOperation("权限添加")
    @PreAuthorize("hasAuthority('system:permission:add')")
    @Log(title = "权限配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(
            @Validated({ValidationGroup.AddGroup.class, Default.class})
            @RequestBody SysPermissionAddDTO permissionDTO
    ) {
        SysPermission permission = new SysPermission();
        BeanUtils.copyProperties(permissionDTO, permission);
        if (!permissionService.checkPermissionNameUnique(permission)) {
            return AjaxResult.error("新增权限'" + permission.getPremissionName() + "'失败，权限名称已存在");
        } else if (!permissionService.checkPermissionKeyUnique(permission)) {
            return AjaxResult.error("新增权限'" + permission.getPerms() + "'失败，权限值已存在");
        }
        return AjaxResult.toAjax(permissionService.insertPermission(permission));
    }

    @ApiOperation(value = "权限修改", notes = "根据ID修改")
    @PreAuthorize("hasAuthority('system:permission:update')")
    @Log(title = "权限配置", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult update(
            @Validated({ValidationGroup.UpdateGroup.class, Default.class})
            @RequestBody SysPermission permission
    ) {
        if (!permissionService.checkPermissionNameUnique(permission)) {
            return AjaxResult.error("新增权限'" + permission.getPremissionName() + "'失败，权限名称已存在");
        } else if (!permissionService.checkPermissionKeyUnique(permission)) {
            return AjaxResult.error("新增权限'" + permission.getPerms() + "'失败，权限值已存在");
        }
        return AjaxResult.toAjax(permissionService.updatePermissionById(permission));
    }

    @ApiOperation(value = "权限删除", notes = "根据ID删除, 可以传递多个")
    @PreAuthorize("hasAuthority('system:permission:remove')")
    @Log(title = "权限配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{permissionIds}")
    public AjaxResult remove(@PathVariable Long[] permissionIds) {
        permissionService.deletePermissionByIds(permissionIds);
        return AjaxResult.success();
    }
}
