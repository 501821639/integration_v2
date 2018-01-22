package com.core.user.dao.impl;

import com.core.user.bean.User;
import com.core.user.dao.UserDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    public void addUser(User user) {
        hibernateTemplate.save(user);
    }

    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    public User getUser(String id) {
        return hibernateTemplate.get(User.class, id);
    }

    public List<?> queryUser(String hql) {
        return hibernateTemplate.find(hql);
    }

}
