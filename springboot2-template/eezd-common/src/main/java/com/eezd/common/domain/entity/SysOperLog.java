package com.eezd.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysOperLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志ID", position = 1)
    @TableId(type = IdType.AUTO)
    private Long operId;

    @ApiModelProperty(value = "操作模块", position = 2)
    private String title;

    @ApiModelProperty(value = "业务类型(0其它 1新增 2修改 3删除)", position = 3, allowableValues = "0,1,2,3")
    private Integer businessType;

    @ApiModelProperty(value = "业务类型数组", position = 4)
    private Integer[] businessTypes;

    @ApiModelProperty(value = "请求方法", position = 5)
    private String method;

    @ApiModelProperty(value = "请求方式", position = 6)
    private String requestMethod;

    @ApiModelProperty(value = "操作类别(0其它 1后台用户 2手机端用户)", position = 7, allowableValues = "0,1,2")
    private Integer operatorType;

    @ApiModelProperty(value = "操作人员", position = 8)
    private String operName;

    @ApiModelProperty(value = "请求URL", position = 9)
    private String operUrl;

    @ApiModelProperty(value = "操作地址", position = 10)
    private String operIp;

    @ApiModelProperty(value = "操作地点", position = 11)
    private String operLocation;

    @ApiModelProperty(value = "请求参数", position = 12)
    private String operParam;

    @ApiModelProperty(value = "返回参数", position = 13)
    private String jsonResult;

    @ApiModelProperty(value = "操作状态(0正常 1异常)", position = 14, allowableValues = "0,1")
    private Integer status;

    @ApiModelProperty(value = "错误消息", position = 15)
    private String errorMsg;

    @ApiModelProperty(value = "操作时间", position = 16)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

    @ApiModelProperty(value = "消耗时间", position = 17)
    private Long costTime;
}
