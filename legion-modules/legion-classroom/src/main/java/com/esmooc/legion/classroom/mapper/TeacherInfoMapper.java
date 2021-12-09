package com.esmooc.legion.classroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.classroom.entity.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 老师信息数据处理层
 * @author Daimao
 */
@Mapper
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {

}
