package com.esmooc.legion.your.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.your.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 呆猫
 *
 * @Date: 2022/03/11/ 13:27
 * @Description: 
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}