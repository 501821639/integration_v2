package com.shiro.realm;

import com.core.user.dto.UserAllMessage;
import com.core.user.service.UserService;
import com.utils.spring.ApplicationContextTool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.List;


/**
 * Created by GSN on 2017/4/5.
 * shiro realm自定义
 */

public class CustomRealm extends AuthorizingRealm {


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        UserAllMessage userAllMessage = (UserAllMessage) principals.getPrimaryPrincipal();

        List<String> permissions = ApplicationContextTool.getBean(UserService.class).queryUserRolePermission(userAllMessage.getId());

        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        sai.addStringPermissions(permissions);

        return sai;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        UserAllMessage userAllMessage = ApplicationContextTool.getBean(UserService.class).requiredLogin(username);

        if (userAllMessage == null) {
            return null;
        }

        if (userAllMessage.getLocked() == 0) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(userAllMessage, userAllMessage.getPassword(), ByteSource.Util.bytes(userAllMessage.getUsername()), this.getName());
    }

    //清除缓存
    public void clearCached() {
        //只能清理当前用户的授权缓存,如何清理所有用户的缓存？
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
