package com.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by GSN on 2017/4/7.
 * 获取spring ApplicationContext
 */
public class ApplicationContextTool implements ApplicationContextAware {


    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextTool.applicationContext = applicationContext;
    }

    public static Object getBean(String var1) {
        return applicationContext.getBean(var1);
    }

    public static <T> T getBean(Class<T> var1) {
        return applicationContext.getBean(var1);
    }


}
