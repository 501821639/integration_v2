package com.core.common.dao.impl;

import com.core.common.dao.SqlDao;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by GSN on 2017/4/11.
 * 暂无描述
 */

@Repository
public class SqlDaoImpl implements SqlDao {

    @Resource
    private HibernateTemplate hibernateTemplate;


    @Override
    public Object querySqlObject(String sql) {

        return hibernateTemplate.execute((HibernateCallback<Object>) session -> {
            Query query = session.createNamedQuery(sql);
            return query.list();
        });

    }
}
