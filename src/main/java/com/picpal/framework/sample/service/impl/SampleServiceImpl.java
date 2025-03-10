package com.picpal.framework.sample.service.impl;

import com.picpal.framework.sample.dto.SampleDTO;
import com.picpal.framework.sample.service.SampleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public List<Map<String, String>> selectUser() throws Exception {
        return null;
    }

    @Override
    public int saveUser(SampleDTO params) throws Exception {
        return 0;
    }
}
