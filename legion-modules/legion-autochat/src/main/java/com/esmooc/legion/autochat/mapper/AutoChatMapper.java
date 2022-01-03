package com.esmooc.legion.autochat.mapper;

import com.esmooc.legion.autochat.entity.AutoChat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AutoChatMapper extends BaseMapper<AutoChat> {

    /**
     * 查询
     * @param question
     * @param pageSize
     * @return
     */
    @Select("SELECT * FROM t_auto_chat WHERE MATCH (title, keywords)" +
            "        AGAINST (#{question})" +
            "        ORDER BY sort_order DESC" +
            "        LIMIT 0,${pageSize}")
    List<AutoChat> find(@Param("question") String question, @Param("pageSize") int pageSize);

    @Select("select * from t_auto_chat where  title =#{title} ;")
    List<AutoChat> findByTitle(String title);
}
