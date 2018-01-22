package com.core.test.service;

/**
 * Created by GSN on 2017/12/4.
 * 测试业务
 *
 * @author 郭少男
 */
public interface TestService {

    /**
     * 生成大数据
     *
     * @param beginValue i的其实值
     * @param endValue   i的结束值
     * @return
     */
    String requiredCreateTest(int beginValue, int endValue);

    /**
     * 查询数据
     *
     * @param hql 要执行hql语句
     * @return
     */
    String queryTest(String hql);

}
