package cn.itcast.bookstore.book.service;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.domain.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public List<Book> findByCategory(String cid) {
        return bookDao.findByCategory(cid);
    }

    public Book load(String bid) {
        return bookDao.load(bid);
    }
}
