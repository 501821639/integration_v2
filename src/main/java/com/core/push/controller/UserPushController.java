package com.core.push.controller;

import com.utils.date.FormatString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 用户数据推送
 * Created by GSN on 2017/10/27.
 *
 * @author 郭少男
 */
@Controller
@RequestMapping("/user_push")
public class UserPushController {


    /**
     * html5 SSE (测试运行结果失败！！！)
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("/login.shtml")
    public void login(HttpServletResponse response) throws IOException {

        response.setContentType("text/event-stream");

        System.out.println("用户请求推送用户状态信息");

        PrintWriter out = response.getWriter();

        out.print(new FormatString().FromatYmdhms(new Date()));
        out.flush();


    }


}
