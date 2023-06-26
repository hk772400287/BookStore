package cn.itcast.bookstore.user.web.filter;

import cn.itcast.bookstore.user.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter",
        urlPatterns = {"/jsps/cart/*", "/jsps/order/*"},
        servletNames = {"OrderServlet", "CartServlet"})
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User)req.getSession().getAttribute("session_user");
        if (user != null) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("msg", "Please log in first");
            req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, response);
        }

    }
}
