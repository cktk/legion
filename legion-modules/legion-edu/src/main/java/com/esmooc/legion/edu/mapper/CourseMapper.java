package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import com.baomidou.mybatisplus.extension.plugins.pagination.Page;import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.vo.HomePageVO;import com.esmooc.legion.edu.entity.vo.StuPdfCourseVO;import com.esmooc.legion.edu.entity.vo.StuVideoCourseVO;import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/01/26/ 14:40
 * @Description:
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 查询课程业务列表
     *
     * @param course 课程业务
     * @return 课程业务集合
     */
    List<Course> selectBizCourseList(Course course);

    IPage<Course> selectBizCourseVOList(@Param("course") Course Course, @Param("page") IPage<?> page);

    IPage<StuVideoCourseVO> stuVideoCourseList(@Param("stuVideoCourse") StuVideoCourseVO stuVideoCourse, @Param("page") Page page);

    IPage<StuPdfCourseVO> stuPdfCourseList(@Param("stuPdfCourse") StuPdfCourseVO stuPdfCourse, @Param("page") Page page);

    List<String> selectBizCourseIds1(@Param("ids") List<String> ids);

    List<String> selectBizCourseIds2(@Param("ids") List<String> ids);

    int selectHomeStatistics(@Param("roleId") int roleId, @Param("learningScope") String learningScope, @Param("courseType") int courseType);

    int selectcourseSize(@Param("courseType") int courseType);

    List<HomePageVO> selectcourse(@Param("courseType") int courseType);

    List<HomePageVO> selectcourse1(@Param("courseType") int courseType, @Param("learningScope") String learningScope);

    String selectcourseVideoPeriod(@Param("learningScope") String learningScope, @Param("userId") long userId);

    StuPdfCourseVO selectcoursePdfPeriod(@Param("learningScope") String learningScope, @Param("userId") Long userId);
}
