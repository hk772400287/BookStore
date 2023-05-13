package cn.itcast.bookstore.user.web.servlet.admin;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminUserServlet", value = "/admin/AdminUserServlet")
public class AdminUserServlet extends BaseServlet {
    public String login(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("adminname");
        String pw = request.getParameter("password");
        if(name.equals("admin") && pw.equals("12345")) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", name);
            return "f:/adminjsps/admin/main.jsp";
        } else {
            request.setAttribute("msg", "您不是管理员");
            return "f:/adminjsps/login.jsp";
        }

    }
}
