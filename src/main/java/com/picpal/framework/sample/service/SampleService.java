package com.picpal.framework.sample.service;

import com.picpal.framework.sample.dto.SampleDTO;

import java.util.List;
import java.util.Map;

public interface SampleService {
    public List<Map<String, String>> selectUser() throws Exception;

    public int saveUser(SampleDTO params) throws Exception;
}
