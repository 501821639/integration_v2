package com.core.test.dao;

import com.core.test.bean.TestOneBean;

import java.util.List;

/**
 * Created by GSN on 2017/12/4.
 */
public interface TestOneDao {

    void addTestOne(TestOneBean testOneBean);

    void deleteTestOne(TestOneBean testOneBean);

    void updateTestOne(TestOneBean testOneBean);

    List<?> findTestOne(String queryString, Object[] values);

    List<?> finTestOne(String queryString);

}
