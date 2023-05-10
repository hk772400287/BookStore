package cn.itcast.bookstore.user.web.servlet;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "userServlet", value = "/userServlet")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserService();

    public String register(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        form.setUid(CommonUtils.uuid());
        form.setCode(CommonUtils.uuid() + CommonUtils.uuid());

        Map<String, String> errormsg = new HashMap<>();
        String username = form.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errormsg.put("username", "用户名不能为空！");
        } else if (username.length() < 3 || username.length() > 10) {
            errormsg.put("username", "用户名长度必须在3-10之间！");
        }
        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errormsg.put("password", "密码不能为空！");
        } else if (password.length() < 3 || password.length() > 10) {
            errormsg.put("password", "密码长度必须在3-10之间！");
        }
        String email = form.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errormsg.put("email", "email不能为空！");
        } else if (email.matches("\\w+@\\w+")) {
            errormsg.put("email", "email格式错误！");
        }

        if (errormsg.size() > 0) {
            request.setAttribute("errors", errormsg);
            request.setAttribute("form", form);
            return "f:/jsps/user/regist.jsp";
        }

        try {
            userService.userRegister(form);
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("form", form);
            return "f:/jsps/user/regist.jsp";
        }
        request.setAttribute("msg", "注册成功！");
        return "f:/jsps/msg.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        User user = null;
        try {
            user = userService.userLogin(form);
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("form", form);
            return "f:/jsps/user/login.jsp";
        }
        HttpSession session = request.getSession();
        session.setAttribute("session_user", user);
        return "r:/index.jsp";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getSession().invalidate();
        return "r:/index.jsp";
    }
}
