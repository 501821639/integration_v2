package com.core.common.dao.impl;


import com.core.common.dao.PagingDao;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by GSN on 2017/4/6.
 * 暂无描述
 */
@Repository
public class PagingDaoImpl implements PagingDao{

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public Object pageQueryObject(String hql, int page, int length) {
        return hibernateTemplate.execute((HibernateCallback<Object>) session -> {
            Query query = session.createQuery(hql);
            query.setFirstResult(page * length);
            query.setMaxResults(length);
            return query.list();
        });
    }

    @Override
    public int PageNumberObject(String hql) {
        return hibernateTemplate.execute(session -> {
            Query query = session.createQuery(hql);
            return Integer.parseInt(query.uniqueResult().toString());
        });
    }
}
