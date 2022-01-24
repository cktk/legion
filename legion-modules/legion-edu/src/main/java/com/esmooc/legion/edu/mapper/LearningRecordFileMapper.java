package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.edu.entity.LearningRecordFile;
import com.esmooc.legion.edu.entity.vo.RankingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程附件学习状态业务Mapper接口
 *
 * @author sun
 * @date 2020-12-28
 */
@Mapper
public interface LearningRecordFileMapper {
    /**
     * 查询课程附件学习状态业务
     *
     * @param courseId 课程附件学习状态业务ID
     * @return 课程附件学习状态业务
     */
    LearningRecordFile selectBizLearningRecordFileById(LearningRecordFile learningRecordFile);

    int selectBizLearningRecordFileSize(LearningRecordFile learningRecordFile);

    /**
     * 查询课程附件学习状态业务列表
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @param page
     * @return 课程附件学习状态业务集合
     */
    IPage<LearningRecordFile> selectBizLearningRecordFileList(LearningRecordFile learningRecordFile, Page page);

    /**
     * 新增课程附件学习状态业务
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @return 结果
     */
    int insertBizLearningRecordFile(LearningRecordFile learningRecordFile);

    /**
     * 修改课程附件学习状态业务
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @return 结果
     */
    int updateBizLearningRecordFile(LearningRecordFile learningRecordFile);

    int updateBizLearningRecordFileTime(LearningRecordFile learningRecordFile);

    /**
     * 删除课程附件学习状态业务
     *
     * @param courseId 课程附件学习状态业务ID
     * @return 结果
     */
    int deleteBizLearningRecordFileById(@Param("courseId") String courseId, @Param("userId") String userId);

    /**
     * 批量删除课程附件学习状态业务
     *
     * @param courseIds 需要删除的数据ID
     * @return 结果
     */
    int deleteBizLearningRecordFileByIds(@Param("courseIds") String[] courseIds, @Param("userId") String userId);

    long selectBizLearningRecordFileTime(LearningRecordFile learningRecordFile);

    List<RankingVO> selectVideoStudentRanking();

    List<RankingVO> selectOpenStudentRanking();

    List<RankingVO> selectVideoUnitRanking();

    List<RankingVO> selectOpenUnitRanking();

}
