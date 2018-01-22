package com.core.test.controller;

import com.core.test.other.EnumState;
import com.core.test.service.TestService;
import com.utils.freemarker.CreateWord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GSN on 2017/9/1.
 * 测试
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    //使用枚举可以防止参数传递错误，当用户传递错误参数程序需会抛出400参数类型错误,适用范围比较广泛例如审核状态，审核流程等
    //http://localhost:8081/test/test1.shtml?enumState=SUBMIT
    @RequestMapping("/test1.shtml")
    public void test1(EnumState enumState) {
        System.out.println(EnumState.CREATE.getValue());
        System.out.println(enumState.getValue());
    }

    /**
     * 生成大数据
     */
    @RequestMapping("/create.shtml")
    public void createTest(int beginValue, int endValue, HttpServletResponse response) throws IOException {
        response.getWriter().print(testService.requiredCreateTest(beginValue, endValue));
    }

    /**
     * 查询数据
     */
    @RequestMapping("/query.shtml")
    public void queryTest(String hql, HttpServletResponse response) throws IOException {
        response.getWriter().print(testService.queryTest(hql));
    }

    @RequestMapping("/free_marker.shtml")
    public void freeMarker(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = request.getSession().getServletContext().getRealPath("/");


        Map map = new HashMap(8);

        map.put("title", "1233");

        new CreateWord(map).start(path + "test/", "F:\\", "kjbgmb-2.ftl");

        response.getWriter().print("success");

    }

}
