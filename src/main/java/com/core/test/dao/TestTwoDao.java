package com.core.test.dao;

import com.core.test.bean.TestTwoBean;

import java.util.List;

/**
 * Created by GSN on 2017/12/4.
 */
public interface TestTwoDao {

    void addTestTwo(TestTwoBean testTwoBean);

    void deleteTestTwo(TestTwoBean testTwoBean);

    void updateTestTwo(TestTwoBean testTwoBean);

    List<?> findTestTwo(String queryString, Object[] values);
}
