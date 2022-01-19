package com.esmooc.legion.open.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.open.entity.LearningRecordFile;
import com.esmooc.legion.open.entity.vo.RankingVO;

import java.util.List;

/**
 * 课程附件学习状态业务Service接口
 *
 * @author sun
 * @date 2020-12-28
 */
public interface LearningRecordFileService {
    /**
     * 查询课程附件学习状态业务
     *
     * @param courseId 课程附件学习状态业务ID
     * @return 课程附件学习状态业务
     */
    public LearningRecordFile selectBizLearningRecordFileById(LearningRecordFile learningRecordFile);

    /**
     * 查询课程附件学习状态业务列表
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @param page
     * @return 课程附件学习状态业务集合
     */
    public IPage<LearningRecordFile> selectBizLearningRecordFileList(LearningRecordFile learningRecordFile, Page page);

    /**
     * 新增课程附件学习状态业务
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @return 结果
     */
    public int insertBizLearningRecordFile(LearningRecordFile learningRecordFile);

    public int insertVideoLearningRecord(LearningRecordFile learningRecordFile) throws Exception;

    public int insertPdfLearningRecord(LearningRecordFile learningRecordFile);

    /**
     * 修改课程附件学习状态业务
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @return 结果
     */
    public int updateBizLearningRecordFile(LearningRecordFile learningRecordFile);

    /**
     * 批量删除课程附件学习状态业务
     *
     * @param courseIds 需要删除的课程附件学习状态业务ID
     * @return 结果
     */
    public int deleteBizLearningRecordFileByIds(String[] courseIds, String userId);

    /**
     * 删除课程附件学习状态业务信息
     *
     * @param courseId 课程附件学习状态业务ID
     * @return 结果
     */
    public int deleteBizLearningRecordFileById(String courseId, String userId);

    public List<RankingVO> selectVideoStudentRanking();

    public List<RankingVO> selectOpenStudentRanking();

    public List<RankingVO> selectVideoUnitRanking();

    public List<RankingVO> selectOpenUnitRanking();
}
