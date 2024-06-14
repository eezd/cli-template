package com.eezd.main.web.dto;

import com.eezd.common.domain.entity.SysRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "角色ADD对象")
@Data
public class SysRoleAddDTO extends SysRole {

    @JsonIgnore
    private Long roleId;
}

