package cn.itcast.bookstore.category.service;

import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();

    public List<Category> showAll() {
        return categoryDao.showAll();
    }
}
