package com.esmooc.legion.classroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.classroom.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程信息数据处理层
 * @author Daimao
 */
@Mapper
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

}
