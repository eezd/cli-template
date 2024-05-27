package com.eezd.main.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "系统配置添加请求体")
@Data
public class SysConfigAddDTO implements Serializable {

    @ApiModelProperty(value = "参数名称", required = true, example = "测试系统配置")
    private String configName;

    @ApiModelProperty(value = "参数键名", required = true, example = "system.config.testApi")
    private String configKey;

    @ApiModelProperty(value = "参数键值", required = true, example = "True")
    private String configValue;

    @ApiModelProperty(value = "系统内置（Y是 N否）", example = "Y")
    private String configType;

    @ApiModelProperty(value = "备注")
    private String remark;
}

