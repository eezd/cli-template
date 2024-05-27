package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
    @ApiModelProperty(value = "参数名称", example = "测试系统配置")
    private String configName;

    /**
     * 参数键名
     */
    @ApiModelProperty(value = "参数键名", example = "system.config.testApi")
    private String configKey;

    /**
     * 参数键值
     */
    @ApiModelProperty(value = "参数键值", example = "True")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @ApiModelProperty(value = "系统内置（Y是 N否）", example = "Y")
    private String configType;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
