package com.core.user.service;


import com.core.user.bean.User;
import com.core.user.dto.UserAllMessage;
import com.core.user.dto.UserAuthResult;

import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface UserService {

    /**
     * 用户注册
     * 生成用户注册验证码(检测账号是否注册)
     *
     * @param key 用户账号
     * @param val 系统生成的验证码
     * @return 生成结果
     */
    UserAuthResult createUserAuth(String key, String val);

    /**
     * 注册
     *
     * @param user        用户实体类
     * @param userAuth    用户输入验证码
     * @param sessionAuth 系统生成验证码
     * @return 回执信息
     */
    String requiredRegisterUser(User user, String userAuth, String sessionAuth);

    /**
     * 查询用户信息
     *
     * @param userName 账号
     * @return AssistPermission封装之后的用户信息
     */
    UserAllMessage requiredLogin(String userName);

    /**
     * 分页数据
     *
     * @param page   页码
     * @param length 长度
     * @param user   条件
     * @return json
     */
    String pageUser(int page, int length, User user);

    /**
     * 页码
     *
     * @param length 长度
     * @param user   条件
     * @return 页码
     */
    int numberUser(int length, User user);

    /**
     * 更新用户头像
     *
     * @param userId   用户id
     * @param portrait url地址
     * @return 回执信息
     */
    String requiredUpdateUserPortrait(String userId, String portrait);

    /**
     * 更新姓名、身份证、邮箱信息，数据库中字段不为null进行更新
     *
     * @param userId 用户id
     * @param card   姓名
     * @param idCard 身份证
     * @param mail   邮箱
     * @return 回执信息
     */
    String requiredUpdateUserICM(String userId, String card, String idCard, String mail);

    /**
     * 清除shiroEhcache缓存
     *
     * @return 回执信息
     */
    String clearCached();

    /**
     * 查询用户所拥有的权限
     *
     * @param userId 用户id
     * @return 权限集合（去重）
     */
    List<String> queryUserRolePermission(String userId);

    /**
     * 锁定账号
     *
     * @param userId       用户id
     * @param lockedRemark 锁定原因
     * @return 回执信息
     */
    String requiredUpdateUserlocked(String userId, String lockedRemark);

    /**
     * 解封账号
     *
     * @param userId 用户id
     * @return 回执信息
     */
    String requiredUpdateUserlocked(String userId);

}
