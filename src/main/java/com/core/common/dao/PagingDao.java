package com.core.common.dao;

/**
 * Created by GSN on 2017/4/6.
 * 暂无描述
 */
public interface PagingDao {

    /**
     * 查询分页数据
     *
     * @param hql 查询语句
     * @param page   页码
     * @param length 每页显示条数
     * @return ListBehavior<R>
     */
    Object pageQueryObject(String hql, int page, int length);

    /**
     * 页码数
     *
     * @param hql 查询语句
     * @return 页码数
     */
    int PageNumberObject(String hql);

}
