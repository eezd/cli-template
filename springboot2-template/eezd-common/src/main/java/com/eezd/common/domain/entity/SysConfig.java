package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysConfig implements Serializable {

    @ApiModelProperty(value = "配置ID")
    @TableId(type = IdType.AUTO)
    private Long configId;

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @ApiModelProperty(value = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @ApiModelProperty(value = "参数键值")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @ApiModelProperty(value = "系统内置（Y是 N否）")
    private String configType;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
