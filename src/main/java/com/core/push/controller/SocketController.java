package com.core.push.controller;

import com.core.push.vo.ServiceUserVo;
import com.core.push.websockey.MyWebSocket;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取web socket相关信息
 * Created by GSN on 2017/10/30.
 *
 * @author 郭少男
 */
@Controller
@RequestMapping("/web_socket")
public class SocketController {

    @RequestMapping("/get_service_users.shtml")
    public void getServiceUser(HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        List<ServiceUserVo> userVos = new ArrayList<>();

        MyWebSocket.map.forEach((key, myWebSocket) -> {

            ServiceUserVo serviceUserVo = new ServiceUserVo();
            serviceUserVo.setUserId(myWebSocket.getUserId());
            serviceUserVo.setCard(myWebSocket.getCard());
            serviceUserVo.setUserName(myWebSocket.getUserName());

            userVos.add(serviceUserVo);

        });

        out.print(new FormatJson().listConversion(userVos));

    }

}
