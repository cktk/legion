package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.edu.entity.LearningRecord;
import com.esmooc.legion.edu.entity.vo.LearningRecordVO;
import com.esmooc.legion.edu.entity.vo.PlaybackFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程学习状态业务Mapper接口
 *
 * @author sun
 * @date 2020-12-28
 */
@Mapper
public interface LearningRecordMapper extends BaseMapper<LearningRecord> {
    /**
     * 查询课程学习状态业务
     *
     * @param courseId 课程学习状态业务ID
     * @return 课程学习状态业务
     */
     LearningRecordVO selectBizLearningRecordById(@Param("courseId") String courseId, @Param("userId") String userId);

     List<String> selectBizLearningRecordCourseId(@Param("userId") String userId);

    /**
     * 查询课程学习状态业务列表
     *
     * @param bizLearningRecord 课程学习状态业务
     * @param page
     * @return 课程学习状态业务集合
     */
     IPage<LearningRecordVO> selectBizLearningRecordList(@Param("learningRecord") LearningRecordVO learningRecord,@Param("page") Page page);

     List<PlaybackFileVO> selectPlaybackFileVOList(@Param("courseId") String courseId, @Param("userId") String userId, @Param("fileType") int fileType);

     int getCountNot0(@Param("userId") String userId);

    /**
     * 新增课程学习状态业务
     *
     * @param learningRecord 课程学习状态业务
     * @return 结果
     */
     int insertBizLearningRecord(LearningRecord learningRecord);

     int insertBizLearningRecords(@Param("courseIds") List<String> courseIds, @Param("userId") String userId, @Param("studyType") Integer studyType, @Param("year") String year);

    /**
     * 修改课程学习状态业务
     *
     * @param learningRecord 课程学习状态业务
     * @return 结果
     */
     int updateBizLearningRecord(LearningRecord learningRecord);

    /**
     * 删除课程学习状态业务
     *
     * @param courseId 课程学习状态业务ID
     * @return 结果
     */
     int deleteBizLearningRecordById(@Param("courseId") String courseId, @Param("userId") String userId);

    /**
     * 批量删除课程学习状态业务
     *
     * @param courseIds 需要删除的数据ID
     * @return 结果
     */
     int deleteBizLearningRecordByIds(@Param("courseIds") String[] courseIds, @Param("userId") String userId);

}
