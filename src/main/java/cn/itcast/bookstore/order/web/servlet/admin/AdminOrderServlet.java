package cn.itcast.bookstore.order.web.servlet.admin;

import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderServlet", value = "/admin/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    private final OrderService orderService = new OrderService();

    public String load(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int state = Integer.parseInt(request.getParameter("state"));
        List<Order> orderList = null;
        if (state == 0) {
            orderList = orderService.loadAllOrders();
        } else {
            orderList = orderService.loadOrdersByState(state);
        }
        request.setAttribute("orderList", orderList);
        return "f:/adminjsps/admin/order/list.jsp";
    }
}
