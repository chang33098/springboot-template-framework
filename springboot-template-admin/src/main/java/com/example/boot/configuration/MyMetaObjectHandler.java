package com.example.boot.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.boot.springboottemplatesecurity.model.UserPrincipal;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * <h1>MyBatisPlus自动填充的功能</h1>
 * <p>新增、更新时自动填充`创建人`、`创建时间`、`编辑人`和`编辑时间`</p>
 *
 * @author Chang
 * @date 2019/11/11 23:43
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal principal = null;
        if (authentication != null) principal = (UserPrincipal) authentication.getPrincipal();

        metaObject.setValue("createTime", new Timestamp(System.currentTimeMillis())); //更新时间
        metaObject.setValue("createBy", principal != null ? principal.getId() : null); //创建人
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal principal = null;
        if (authentication != null)
            principal = (UserPrincipal) authentication.getPrincipal();

        metaObject.setValue("updateTime", new Timestamp(System.currentTimeMillis())); //更新时间
        metaObject.setValue("updateBy", principal != null ? principal.getId() : null); //创建人
    }
}
