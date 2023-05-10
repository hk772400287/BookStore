package cn.itcast.bookstore.category.web.servlet;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private final CategoryService categoryService = new CategoryService();

    public String showAll(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Category> categoryList = categoryService.showAll();
        request.setAttribute("categoryList", categoryList);
        return "f:/jsps/left.jsp";
    }
}
