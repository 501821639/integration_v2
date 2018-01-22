package com.core.user.controller;

import com.core.user.bean.User;
import com.core.user.dto.UserAllMessage;
import com.core.user.dto.UserAuthResult;
import com.core.user.service.UserService;
import com.utils.date.FormatType;
import com.utils.file.Read;
import com.utils.json.FormatJson;
import com.utils.upload.SpringMvcUpload;
import com.utils.upload.UploadType;
import com.utils.upload.UploadUrl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //生成用户注册验证码
    @RequestMapping("/auth.shtml")
    public void createUserAuth(HttpServletResponse response, HttpSession session, String username) throws IOException {
        UserAuthResult userAuthResult = userService.createUserAuth(username, (String) session.getAttribute(username));
        if (userAuthResult.getCode().equals("success")) {
            session.setMaxInactiveInterval(600000);
            session.setAttribute(username, userAuthResult.getAuth() + "," + userAuthResult.getDate().getTime());
            userAuthResult.setAuth(null);
        }
        response.getWriter().print(new FormatJson().objectConversion(userAuthResult));
    }

    //注册
    @RequestMapping("/register.shtml")
    public void register(HttpServletResponse response, HttpSession session, User user, String userAuth) throws IOException {
        response.getWriter().print(userService.requiredRegisterUser(user, userAuth, (String) session.getAttribute(user.getUsername())));
    }

    //登录
    @RequestMapping("/login.shtml")
    public String login(HttpServletRequest request) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                request.setAttribute("error", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                request.setAttribute("error", "密码错误");
            } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                request.setAttribute("error", "账号已被锁定");
            } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
                request.setAttribute("error", "账号登陆次数超过限制,锁定10分钟");
            } else {
                request.setAttribute("error", "系统出现未知错误");
            }
        }
        return "/login.jsp";
    }

    //分页查询用户
    @RequestMapping("/pageuser.shtml")
    @RequiresPermissions("user:find")
    public void pageUser(HttpServletResponse response, int page, int length, User user) throws IOException {
        response.getWriter().print(userService.pageUser(page, length, user));
    }

    @RequestMapping("/numberuser.shtml")
    @RequiresPermissions("user:find")
    public void numberUser(HttpServletResponse response, int length, User user) throws IOException {
        response.getWriter().print(userService.numberUser(length, user));
    }

    //用户更换头像
    @RequestMapping("/portrait.shtml")
    @RequiresPermissions("user:portrait")
    public void userPortrait(@RequestParam("file") CommonsMultipartFile cmFile, HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();

        if (!new UploadType().verification("image", cmFile.getOriginalFilename())) {
            out.print("不支持的文件类型");
        } else if (cmFile.getSize() > 1024 * 1024) {
            out.print("大小限制1MB");
        } else {
            String userPortrait = new UploadUrl().getValue("USER_PORTRAIT") + user.getId() + "/";
            String path = request.getSession().getServletContext().getRealPath("/") + userPortrait;
            try {
                String fileName = new SpringMvcUpload(cmFile).run(path, 0);
                String result = userService.requiredUpdateUserPortrait(user.getId(), "/" + userPortrait + fileName);
                if (result.equals("success")) {
                    out.print("success");
                } else {
                    out.print(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print("上传失败");
            }
        }

    }

    //读取用户历史图像
    @RequestMapping("/history-portrait.shtml")
    @RequiresPermissions("user:historyportrait")
    public void userHistoryPortrait(HttpServletResponse response, HttpServletRequest request) throws IOException {

        PrintWriter out = response.getWriter();
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();
        String userPortrait = new UploadUrl().getValue("USER_PORTRAIT") + user.getId();
        String path = request.getSession().getServletContext().getRealPath("/") + userPortrait;

        String[] files = new Read().getFileAll(path);

        List<String> list = new ArrayList<>();

        if (files != null) {
            for (String file : files) {
                list.add("/" + userPortrait + "/" + file);
            }
        }

        out.print(new FormatJson().listConversion(list));

    }

    //更新用户姓名、身份证、邮箱信息
    @RequestMapping("/imc.shtml")
    @RequiresPermissions("user:imc")
    public void userIMC(HttpServletResponse response, String card, String idCard, String mail) throws IOException {
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();
        response.getWriter().print(userService.requiredUpdateUserICM(user.getId(), card, idCard, mail));

    }

    //清除shiro授权缓存
    @RequestMapping("clear-cached.shtml")
    @RequiresPermissions("user:clearcached")
    public void clearCached(HttpServletResponse response) throws IOException {
        response.getWriter().print(userService.clearCached());
    }

    //获取用户所有信息
    @RequestMapping("/allMessage.shtml")
    public void allMessage(HttpServletResponse response) throws IOException {
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();
        response.getWriter().print(new FormatJson(new FormatType().getYmdhms()).objectConversion(user));
    }

    //锁定账号
    @RequestMapping("locked0.shtml")
    @RequiresPermissions("user:locked0")
    public void userUpdateLocked(HttpServletResponse response, String userId, String lockedRemark) throws IOException {
        response.getWriter().print(userService.requiredUpdateUserlocked(userId, lockedRemark));
    }

    //解锁账号
    @RequestMapping("locked1.shtml")
    @RequiresPermissions("user:locked1")
    public void userUpdateLocked(HttpServletResponse response, String userId) throws IOException {
        response.getWriter().print(userService.requiredUpdateUserlocked(userId));
    }


}
