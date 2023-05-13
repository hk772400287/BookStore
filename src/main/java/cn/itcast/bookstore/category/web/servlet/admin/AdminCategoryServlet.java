package cn.itcast.bookstore.category.web.servlet.admin;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.domain.CategoryException;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet", value = "/admin/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    private final CategoryService categoryService = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Category> categoryList =  categoryService.showAll();
        request.setAttribute("categoryList", categoryList);
        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        category.setCid(CommonUtils.uuid());
        categoryService.add(category);
        return findAll(request, response);
    }

    public String delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String cid = request.getParameter("cid");
        try {
            categoryService.delete(cid);
            return findAll(request, response);
        } catch (CategoryException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
    }

    public String editpre(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String cid = request.getParameter("cid");
        Category category = categoryService.getByCid(cid);
        request.setAttribute("category", category);
        return "f:/adminjsps/admin/category/mod.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Category editedCategory = CommonUtils.toBean(request.getParameterMap(), Category.class);
        categoryService.edit(editedCategory);
        return findAll(request, response);
    }
}
