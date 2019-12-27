package com.atguigu.guli.service.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lzc
 * @create 2019-12-27 2:22
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("自动填充：第一次创建时，生成的时间");
        Date now = new Date();
        this.setFieldValByName("gmtCreate", now, metaObject);
        this.setFieldValByName("gmtModified", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("自动填充：每次更新时，生成的时间");
        Date now = new Date();
        this.setFieldValByName("gmtModified", now, metaObject);
    }
}
