package com.wxc.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface DemoMapper {
    @Select("select 1")
    boolean getBool();
}
