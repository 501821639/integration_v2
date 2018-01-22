package com.core.test.dao.impl;

import com.core.test.bean.TestOneBean;
import com.core.test.dao.TestOneDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/12/4.
 */

@Repository
public class TestOneDaoImpl implements TestOneDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addTestOne(TestOneBean testOneBean) {
        hibernateTemplate.save(testOneBean);
    }

    @Override
    public void deleteTestOne(TestOneBean testOneBean) {
        hibernateTemplate.delete(testOneBean);
    }

    @Override
    public void updateTestOne(TestOneBean testOneBean) {
        hibernateTemplate.update(testOneBean);
    }

    @Override
    public List<?> findTestOne(String queryString, Object[] values) {
        return hibernateTemplate.find(queryString, values);
    }

    @Override
    public List<?> finTestOne(String queryString) {
        return hibernateTemplate.find(queryString);
    }
}
