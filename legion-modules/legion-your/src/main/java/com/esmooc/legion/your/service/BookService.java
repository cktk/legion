package com.esmooc.legion.your.service;

import com.esmooc.legion.your.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/25/ 00:44
 * @Description:
 */
public interface BookService extends IService<Book>{


    Book getByUrl(String url);

    List<Book> listByO();
    List<Book> listByApp();

    List<Book> getByPid(String id);
}
