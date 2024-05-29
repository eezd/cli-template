package com.eezd.main.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "系统配置更新请求体")
@Data
public class SysConfigUpdateDTO implements Serializable {

    @ApiModelProperty(value = "配置ID", required = true, example = "2")
    private Long configId;

    @ApiModelProperty(value = "参数名称")
    private String configName;

    @ApiModelProperty(value = "参数键名")
    private String configKey;

    @ApiModelProperty(value = "参数键值")
    private String configValue;

    @ApiModelProperty(value = "系统内置（Y是 N否）")
    private String configType;

    @ApiModelProperty(value = "备注")
    private String remark;
}
