package cn.itcast.bookstore.order.web.servlet;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderException;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    private final OrderService orderService = new OrderService();

    public String add(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Order order = new Order();
        order.setOid(CommonUtils.uuid());
        order.setOrdertime(LocalDateTime.now());
        order.setState(1);
        order.setTotal(cart.getTotal());
        order.setOwner((User)request.getSession().getAttribute("session_user"));
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubTotal());
            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItemList.add(orderItem);
        }
        order.setOrderItemList(orderItemList);
        orderService.add(order);
        cart.clear();
        request.setAttribute("order", order);
        return "f:/jsps/order/desc.jsp";
    }

    public String myOrders(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User user = (User)request.getSession().getAttribute("session_user");
        String uid = user.getUid();
        List<Order> orders = orderService.myOrders(uid);
        request.setAttribute("orders", orders);
        return "f:/jsps/order/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String oid = (String)request.getParameter("oid");
        Order order = orderService.loadOrder(oid);
        request.setAttribute("order", order);
        return "f:/jsps/order/desc.jsp";
    }

    public String confirm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String oid = (String)request.getParameter("oid");
        try {
            orderService.confirm(oid);
            request.setAttribute("msg", "successfully！");
        } catch (OrderException e) {
            request.setAttribute("msg", e.getMessage());
        }
        return "f:/jsps/order/msg.jsp";
    }

    public String pay(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String oid = request.getParameter("oid");
        String address = request.getParameter("address");
        orderService.addAddress(address, oid);
        orderService.pay(oid);
        request.setAttribute("msg", "Payed successfully！");
        return "f:/jsps/order/msg.jsp";
    }
}
