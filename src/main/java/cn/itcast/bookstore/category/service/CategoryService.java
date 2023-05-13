package cn.itcast.bookstore.category.service;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.domain.CategoryException;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();
    private final BookDao bookDao = new BookDao();

    public List<Category> showAll() {
        return categoryDao.showAll();
    }

    public void add(Category c) {
        categoryDao.add(c);
    }

    public void delete(String cid) throws CategoryException {
        int count = bookDao.getBookCountByCid(cid);
        if (count > 0) {
            throw new CategoryException("该分类还有图书，无法删除！");
        }
        categoryDao.delete(cid);
    }

    public Category getByCid(String cid) {
        return categoryDao.getByCid(cid);
    }

    public void edit(Category editedCategory) {
        categoryDao.edit(editedCategory);
    }
}
