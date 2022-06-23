package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.MessageSend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 消息发送数据处理层
 *
 * @author DaiMao
 */
public interface MessageSendMapper extends BaseMapper<MessageSend> {

    /**
     * 通过消息id删除
     *
     * @param messageId
     */
    @Select("delete from t_message_send m where m.message_id = #{messageId}")
    void deleteByMessageId(String messageId);

    /**
     * 批量更新消息状态
     *
     * @param userId
     * @param status
     */
    @Update("update t_message_send m set m.status=#{status} where m.user_id=#{userId}")
    void updateStatusByUserId(@Param("userId") String userId, @Param("status") Integer status);

    /**
     * 通过userId删除
     *
     * @param userId
     * @param status
     */
    @Delete("delete from t_message_send m where m.user_id = #{userId} and m.status=#{status}")
    void deleteByUserId(@Param("userId")  String userId,  @Param("status")  Integer status);
}
