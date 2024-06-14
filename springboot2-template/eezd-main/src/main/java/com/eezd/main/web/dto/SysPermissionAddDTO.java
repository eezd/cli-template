package com.eezd.main.web.dto;

import com.eezd.common.domain.entity.SysPermission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "权限ADD对象")
@Data
public class SysPermissionAddDTO extends SysPermission {

    @JsonIgnore
    private Long premissionId;
}

