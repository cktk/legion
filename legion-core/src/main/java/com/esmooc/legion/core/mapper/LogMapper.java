package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Log;
import org.apache.ibatis.annotations.Delete;

/**
 * 日志数据处理层
 *
 * @author DaiMao
 */
public interface LogMapper extends BaseMapper<Log> {

    @Delete("truncate table t_log ")
    void deleteAll();

}
