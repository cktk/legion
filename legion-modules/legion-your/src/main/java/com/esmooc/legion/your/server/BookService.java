package com.esmooc.legion.your.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.your.entity.Book;
    /**
 * @Author 呆猫
 *
 * @Date: 2022/03/11/ 13:27
 * @Description:
 */
public interface BookService extends IService<Book>{


        void decrypt();


    }
