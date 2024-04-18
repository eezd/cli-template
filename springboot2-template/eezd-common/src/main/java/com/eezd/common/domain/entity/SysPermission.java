package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {
    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    @TableId(type = IdType.AUTO)
    private Long premissionId;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String premissionName;

    /**
     * 权限字符串
     */
    @ApiModelProperty(value = "权限字符串")
    private String perms;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
