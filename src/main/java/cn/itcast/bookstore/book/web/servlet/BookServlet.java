package cn.itcast.bookstore.book.web.servlet;

import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookService();

    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("bookList",bookService.findAll());
        return "f:/jsps/book/list.jsp";
    }

    public String findByCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String cid = request.getParameter("cid");
        request.setAttribute("bookList",bookService.findByCategory(cid));
        return "f:/jsps/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String bid = request.getParameter("bid");
        request.setAttribute("book",bookService.load(bid));
        return "f:/jsps/book/desc.jsp";
    }
}
