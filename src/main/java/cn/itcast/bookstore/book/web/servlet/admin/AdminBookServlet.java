package cn.itcast.bookstore.book.web.servlet.admin;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "AdminBookServlet", value = "/admin/AdminBookServlet")
@MultipartConfig
public class AdminBookServlet extends BaseServlet {
    private final BookService bookService = new BookService();
    private final CategoryService categoryService = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("bookList", bookService.findAll());
        return "f:/adminjsps/admin/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        request.setAttribute("book", book);
        request.setAttribute("categoryList", categoryService.showAll());
        return "f:/adminjsps/admin/book/desc.jsp";
    }

    public String addpre(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("categoryList", categoryService.showAll());
        return "f:/adminjsps/admin/book/add.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        book.setCategory(category);
        book.setBid(CommonUtils.uuid());
        String savePath = this.getServletContext().getRealPath("/book_img");
        Part imagePart = request.getPart("image");
        String fileName = CommonUtils.uuid() + "_" + imagePart.getSubmittedFileName();
        imagePart.write(savePath + "/" + fileName);
        book.setImage("book_img/" + fileName);
        bookService.add(book);
        File imageFile = new File(savePath, fileName);
        Image image = new ImageIcon(imageFile.getAbsolutePath()).getImage();
        if (image.getWidth(null) > 200 || image.getHeight(null) > 200) {
            imageFile.delete();
            request.setAttribute("msg", "图片太大");
            return "f:/adminjsps/admin/book/add.jsp";
        }
        return findAll(request, response);
    }

    public String delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String bid = request.getParameter("bid");
        bookService.delete(bid);
        return findAll(request, response);
    }

    public String edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        book.setCategory(category);
        bookService.edit(book);
        return findAll(request, response);
    }
}
