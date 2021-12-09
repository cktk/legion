package com.esmooc.legion.classroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.classroom.entity.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生信息数据处理层
 * @author Daimao
 */
@Mapper
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {

}
