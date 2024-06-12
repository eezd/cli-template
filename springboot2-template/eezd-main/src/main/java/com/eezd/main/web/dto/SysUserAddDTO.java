package com.eezd.main.web.dto;

import com.eezd.common.domain.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "系统配置ADD对象")
@Data
public class SysUserAddDTO extends SysUser {

    @JsonIgnore
    private Long userId;
}

