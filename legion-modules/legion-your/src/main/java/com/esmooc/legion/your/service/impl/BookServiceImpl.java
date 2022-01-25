package com.esmooc.legion.your.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.your.entity.Book;
import com.esmooc.legion.your.mapper.BookMapper;
import com.esmooc.legion.your.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/25/ 00:44
 * @Description:
 */
@DS("edu")
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public Book getByUrl(String url) {

        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.lambda().eq(Book::getUrl, url);

        return   this.getOne(bookQueryWrapper);
    }

    @Override
    public List<Book> listByO() {

        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.lambda().eq(Book::getType, "1");
        bookQueryWrapper.lambda().eq(Book::getPid, 0);

        return  this.list(bookQueryWrapper);
    }

    @Override
    public List<Book> listByApp() {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.lambda().like(Book::getUrl, "/app#");
        return  this.list(bookQueryWrapper);
    }

    @Override
    public List<Book> getByPid(String id) {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.lambda().eq(Book::getPid,id);
        return  this.list(bookQueryWrapper);
    }
}
