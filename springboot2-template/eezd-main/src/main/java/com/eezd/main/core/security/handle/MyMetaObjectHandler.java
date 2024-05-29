package com.eezd.main.core.security.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.eezd.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getUsername());
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 跳过记录登录信息的更新
        if (!(metaObject.hasSetter("loginIp") || metaObject.hasSetter("loginDate"))) {
            this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getUsername());
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        }
    }
}