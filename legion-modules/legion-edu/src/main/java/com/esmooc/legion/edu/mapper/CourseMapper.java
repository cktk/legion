package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.vo.CourseVO;
import com.esmooc.legion.edu.entity.vo.HomePageVO;
import com.esmooc.legion.edu.entity.vo.StuPdfCourseVO;
import com.esmooc.legion.edu.entity.vo.StuVideoCourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程业务Mapper接口
 *
 * @author sun
 * @date 2020-12-28
 */

@Mapper
public interface CourseMapper {
    /**
     * 查询课程业务
     *
     * @param id 课程业务ID
     * @return 课程业务
     */
    Course selectBizCourseById(String id);

    /**
     * 查询课程业务列表
     *
     * @param course 课程业务
     * @return 课程业务集合
     */
    List<Course> selectBizCourseList(Course course);

    IPage<CourseVO> selectBizCourseVOList(@Param("course") CourseVO Course, @Param("page") IPage<?> page);

    /**
     * 新增课程业务
     *
     * @param course 课程业务
     * @return 结果
     */
    int insertBizCourse(Course course);

    /**
     * 修改课程业务
     *
     * @param course 课程业务
     * @return 结果
     */
    int updateBizCourse(Course course);

    int deleteBizCourse(Course course);

    /**
     * 删除课程业务
     *
     * @param id 课程业务ID
     * @return 结果
     */
    int deleteBizCourseById(String id);

    /**
     * 批量删除课程业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizCourseByIds(String[] ids);

    IPage<StuVideoCourseVO> stuVideoCourseList(@Param("stuVideoCourse") StuVideoCourseVO stuVideoCourse, @Param("page")  Page page);

    IPage<StuPdfCourseVO> stuPdfCourseList(@Param("stuPdfCourse")  StuPdfCourseVO stuPdfCourse,@Param("page")  Page page);

    List<String> selectBizCourseIds1(@Param("ids") List<String> ids);

    List<String> selectBizCourseIds2(@Param("ids") List<String> ids);

    int selectHomeStatistics(@Param("roleId") int roleId, @Param("learningScope") String learningScope, @Param("courseType") int courseType);

    int selectcourseSize(@Param("courseType") int courseType);

    List<HomePageVO> selectcourse(@Param("courseType") int courseType);

    List<HomePageVO> selectcourse1(@Param("courseType") int courseType, @Param("learningScope") String learningScope);

    String selectcourseVideoPeriod(@Param("learningScope") String learningScope, @Param("userId") long userId);

    StuPdfCourseVO selectcoursePdfPeriod(@Param("learningScope") String learningScope, @Param("userId") Long userId);
}
