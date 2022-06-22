package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Message;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 消息内容数据处理层
 *
 * @author DaiMao
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 通过创建发送标识获取
     *
     * @param createSend
     * @return
     */
    @Select("select * from t_message where create_send =#{createSend}")
    List<Message> findByCreateSend(Boolean createSend);
}
