package com.esmooc.legion.your.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.your.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 测试数据处理层
 * @author Daimao
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {

}
