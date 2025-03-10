package com.picpal.framework.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SampleMapper {
    void insertUser(Map<String, Object> record);

    void selectUser(Map<String, Object> record);
}
