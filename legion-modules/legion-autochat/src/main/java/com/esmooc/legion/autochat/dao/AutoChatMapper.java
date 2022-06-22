package com.esmooc.legion.autochat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.autochat.entity.AutoChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AutoChatMapper extends BaseMapper<AutoChat> {

    /**
     * 查询
     *
     * @param question
     * @param pageSize
     * @return
     */
    List<AutoChat> find(@Param("question") String question, @Param("pageSize") int pageSize);
    @Select("select * from  t_auto_chat where  title =#{title}")
    List<AutoChat> findByTitle(String title);
}
