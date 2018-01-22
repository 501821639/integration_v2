package com.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by GSN on 2017/4/21.
 * shiro认证
 * 1.用于限制登录次数过多
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {


    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        String username = (String) token.getPrincipal();

        AtomicInteger retryCount = passwordRetryCache.get(username);

        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }


        int count = retryCount.incrementAndGet();

        if (count > 5) {
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);

        if (matches) {
            passwordRetryCache.remove(username);
        }

        return matches;
    }
}
