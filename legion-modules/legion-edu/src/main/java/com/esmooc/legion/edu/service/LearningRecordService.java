package com.esmooc.legion.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.edu.entity.LearningRecord;
import com.esmooc.legion.edu.entity.vo.LearningRecordVO;
import com.esmooc.legion.edu.entity.vo.PlaybackFileVO;

import java.util.List;

/**
 * 课程学习状态业务Service接口
 *
 * @author sun
 * @date 2020-12-28
 */
public interface LearningRecordService  extends IService<LearningRecord> {
    /**
     * 查询课程学习状态业务
     *
     * @param courseId 课程学习状态业务ID
     * @return 课程学习状态业务
     */
    public LearningRecordVO selectBizLearningRecordById(String courseId, String userId);

    /**
     * 查询课程学习状态业务列表
     *
     * @param bizLearningRecord 课程学习状态业务
     * @param currUser
     * @return 课程学习状态业务集合
     */
    IPage<LearningRecordVO> selectLearningRecordList(LearningRecordVO bizLearningRecord, Page page);

     List<PlaybackFileVO> selectPlaybackFileVOList(String courseId, String userId, int fileType);

    /**
     * 新增课程学习状态业务
     *
     * @param learningRecord 课程学习状态业务
     * @return 结果
     */
    public int insertBizLearningRecord(LearningRecord learningRecord);

    /**
     * 修改课程学习状态业务
     *
     * @param learningRecord 课程学习状态业务
     * @return 结果
     */
    public int updateBizLearningRecord(LearningRecord learningRecord);

    public int ClearingLearningRecord(String courseId, String userId);

    /**
     * 批量删除课程学习状态业务
     *
     * @param courseIds 需要删除的课程学习状态业务ID
     * @return 结果
     */
    public int deleteBizLearningRecordByIds(String[] courseIds, String userId);

    /**
     * 删除课程学习状态业务信息
     *
     * @param courseId 课程学习状态业务ID
     * @return 结果
     */
    public int deleteBizLearningRecordById(String courseId, String userId);

}
