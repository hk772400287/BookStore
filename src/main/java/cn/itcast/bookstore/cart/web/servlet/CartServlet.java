package cn.itcast.bookstore.cart.web.servlet;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        String bid = request.getParameter("bid");
        int count = Integer.parseInt(request.getParameter("count"));
        Book book = new BookService().load(bid);
        CartItem item = new CartItem();
        item.setCount(count);
        item.setBook(book);
        cart.add(item);
        return "f:/jsps/cart/list.jsp";
    }

    public String clear(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.clear();
        return "f:/jsps/cart/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        String bid = request.getParameter("bid");
        cart.delete(bid);
        return "f:/jsps/cart/list.jsp";
    }
}
