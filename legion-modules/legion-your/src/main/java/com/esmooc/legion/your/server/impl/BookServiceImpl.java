package com.esmooc.legion.your.server.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.your.mapper.BookMapper;
import com.esmooc.legion.your.entity.Book;
import com.esmooc.legion.your.server.BookService;
/**
 * @Author 呆猫
 *
 * @Date: 2022/03/11/ 13:27
 * @Description: 
 */
@Slf4j
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService{

    @Override
    public void decrypt() {


        JSONObject book = JSONUtil.parseObj(this.getById(2515).getData());

        JSONObject data = book.getJSONObject("data");
        String quiz = data.getStr("quiz");

        String bookJson = Base64.decodeStr(quiz);
        log.info(bookJson);
        JSONArray jsonArray = JSONUtil.parseArray(bookJson);
        System.out.println("---");
        System.out.println("---");
        System.out.println("---");
        System.out.println("---");
        System.out.println("---");

        System.out.println(jsonArray.get(0));


    }
}
