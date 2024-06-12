package com.eezd.main.web.system.controller;

import com.eezd.common.annotation.Log;
import com.eezd.common.domain.AjaxResult;
import com.eezd.common.domain.ValidationGroup;
import com.eezd.common.domain.entity.SysConfig;
import com.eezd.common.enums.BusinessType;
import com.eezd.main.web.dto.SysConfigAddDTO;
import com.eezd.main.web.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@Api(tags = "系统配置")
@RestController
@RequestMapping("/system/config")
public class SysConfigController {

    @Autowired
    private ISysConfigService configService;

    @ApiOperation("系统配置查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configId", value = "配置ID", dataType = "int", paramType = "query ", example = "1"),
            @ApiImplicitParam(name = "configKey", value = "配置键", dataType = "string", paramType = "query", example = "sys.index.skinName")
    })
    @PreAuthorize("hasAuthority('system:config:query')")
    @GetMapping("/query")
    public AjaxResult select(
            @RequestParam(required = false) Long configId,
            @RequestParam(required = false) String configKey
    ) {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigId(configId);
        sysConfig.setConfigKey(configKey);
        List<SysConfig> list = configService.selectConfigList(sysConfig);
        return AjaxResult.success(list);
    }

    @ApiOperation("系统配置添加")
    @PreAuthorize("hasAuthority('system:config:add')")
    @Log(title = "系统配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(
            @Validated({ValidationGroup.AddGroup.class, Default.class})
            @RequestBody SysConfigAddDTO sysConfig
    ) {
        if (!configService.checkConfigKeyUnique(sysConfig)) {
            return AjaxResult.error("新增参数'" + sysConfig.getConfigName() + "'失败，参数键名已存在");
        }
        return AjaxResult.toAjax(configService.insertConfig(sysConfig));
    }


    @ApiOperation(value = "系统配置修改", notes = "根据ID修改")
    @PreAuthorize("hasAuthority('system:config:update')")
    @Log(title = "系统配置", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult update(
            @Validated({ValidationGroup.UpdateGroup.class, Default.class})
            @RequestBody SysConfig sysConfig
    ) {
        if (!configService.checkConfigKeyUnique(sysConfig)) {
            return AjaxResult.error("修改参数'" + sysConfig.getConfigName() + "'失败，参数键名已存在");
        }
        return AjaxResult.toAjax(configService.updateConfig(sysConfig));
    }

    @ApiOperation(value = "系统配置删除", notes = "根据ID删除, 可以传递多个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configIds", value = "配置ID", dataType = "int", allowMultiple = true, paramType = "path", example = "11,12"),
    })
    @PreAuthorize("hasAuthority('system:config:remove')")
    @Log(title = "系统配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds) {
        configService.deleteConfigByIds(configIds);
        return AjaxResult.success();
    }


    @ApiOperation("刷新缓存")
    @PreAuthorize("hasAuthority('system:config:remove')")
    @Log(title = "系统配置", businessType = BusinessType.CLEAN)
    @PostMapping("/refreshCache")
    public AjaxResult refreshCache() {
        configService.resetConfigCache();
        return AjaxResult.success();
    }
}
