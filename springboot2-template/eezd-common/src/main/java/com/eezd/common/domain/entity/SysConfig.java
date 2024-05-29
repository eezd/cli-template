package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.eezd.common.constant.ValidationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "SysConfig")
@Data
public class SysConfig implements Serializable {

    @ApiModelProperty(value = "配置ID", position = 1)
    @TableId(type = IdType.AUTO)
    private Long configId;

    @ApiModelProperty(value = "参数名称", position = 2, example = "测试系统配置")
    private String configName;

    @ApiModelProperty(value = "参数键名", position = 3, example = "system.config.testApi")
    private String configKey;

    @ApiModelProperty(value = "参数键值", position = 4, example = "True")
    private String configValue;

    @ApiModelProperty(value = "系统内置(Y是 N否)", position = 5, allowableValues = "Y,N", example = "Y")
    @Pattern(regexp = ValidationConstant.Y_OR_N, message = ValidationConstant.Y_OR_N_MSG)
    private String configType;

    @ApiModelProperty(value = "创建人", hidden = true, position = 6)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间", hidden = true, position = 7)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新人", hidden = true, position = 8)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间", hidden = true, position = 9)
    @Null(message = ValidationConstant.EMPTY_MSG)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "备注", position = 10)
    private String remark;
}
