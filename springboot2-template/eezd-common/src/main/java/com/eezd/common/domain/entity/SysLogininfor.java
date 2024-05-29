package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysLogininfor implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录日志ID", position = 1)
    @TableId(type = IdType.AUTO)
    private Long infoId;

    @ApiModelProperty(value = "用户账号", position = 2)
    private String userName;

    @ApiModelProperty(value = "登录状态 0成功 1失败", allowableValues = "0,1", position = 3)
    private String status;

    @ApiModelProperty(value = "登录IP地址", position = 4)
    private String ipaddr;

    @ApiModelProperty(value = "登录地点", position = 5)
    private String loginLocation;

    @ApiModelProperty(value = "浏览器类型", position = 6)
    private String browser;

    @ApiModelProperty(value = "操作系统", position = 7)
    private String os;

    @ApiModelProperty(value = "提示消息", position = 8)
    private String msg;

    @ApiModelProperty(value = "访问时间", position = 9)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
}
