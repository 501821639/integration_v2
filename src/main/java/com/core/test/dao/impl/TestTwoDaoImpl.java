package com.core.test.dao.impl;

import com.core.test.bean.TestTwoBean;
import com.core.test.dao.TestTwoDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/12/4.
 */

@Repository
public class TestTwoDaoImpl implements TestTwoDao {


    @Resource
    private HibernateTemplate hibernateTemplate;


    @Override
    public void addTestTwo(TestTwoBean testTwoBean) {
        hibernateTemplate.save(testTwoBean);
    }

    @Override
    public void deleteTestTwo(TestTwoBean testTwoBean) {
        hibernateTemplate.delete(testTwoBean);
    }

    @Override
    public void updateTestTwo(TestTwoBean testTwoBean) {
        hibernateTemplate.update(testTwoBean);
    }

    @Override
    public List<?> findTestTwo(String queryString, Object[] values) {
        return hibernateTemplate.find(queryString, values);
    }
}
