package com.lagou.service;

import com.lagou.domain.Test;

import java.util.List;

public interface TestService {

    /*
    查询test表所有
     */
    public List<Test> findAllTest();
}
