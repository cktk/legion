package com.esmooc.legion.classroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.classroom.entity.StudentCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 选课中间表数据处理层
 * @author Daimao
 */
@Mapper
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

}
