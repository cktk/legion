package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.entity.LearningRecord;
import com.esmooc.legion.edu.entity.vo.LearningRecordVO;
import com.esmooc.legion.edu.entity.vo.PlaybackFileVO;
import com.esmooc.legion.edu.mapper.CourseMapper;
import com.esmooc.legion.edu.mapper.LearningRecordFileMapper;
import com.esmooc.legion.edu.mapper.LearningRecordMapper;
import com.esmooc.legion.edu.service.LearningRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程学习状态业务Service业务层处理
 *
 * @author sun
 * @date 2020-12-28
 */
@Service
public class LearningRecordServiceImpl extends ServiceImpl<LearningRecordMapper, LearningRecord> implements LearningRecordService {
    @Autowired
    private LearningRecordMapper learningRecordMapper;

    @Autowired
    private LearningRecordFileMapper learningRecordFileMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 查询课程学习状态业务
     *
     * @param courseId 课程学习状态业务ID
     * @return 课程学习状态业务
     */
    @Override
    public LearningRecordVO selectBizLearningRecordById(String courseId, String userId) {
        return learningRecordMapper.selectBizLearningRecordById(courseId, userId);
    }

    /**
     * 查询课程学习状态业务列表
     *
     * @param bizLearningRecord 课程学习状态业务
     * @return 课程学习状态业务
     */
    @Override
    public IPage<LearningRecordVO> selectBizLearningRecordList(LearningRecordVO bizLearningRecord,  Page page) {
        String learningScope = securityUtil.getCurrUser().getType() + "";

        List<String> list = learningRecordMapper.selectBizLearningRecordCourseId(bizLearningRecord.getUserId());
        List<String> ids = new ArrayList<>();
        if (Constants.INTERNALSTUDENTS.toString().equals(learningScope)) {
            ids = courseMapper.selectBizCourseIds1(list);
        } else if (Constants.EXTERNALSTUDENTS.toString().equals(learningScope)) {
            ids = courseMapper.selectBizCourseIds2(list);
        }
        if (ids.size() > 0) {
            learningRecordMapper.insertBizLearningRecords(ids, bizLearningRecord.getUserId(), Constants.NOSTUDY, BaseUtils.getThisYear());
        }


        bizLearningRecord.setLearningScope(learningScope);
        IPage<LearningRecordVO> voList = learningRecordMapper.selectBizLearningRecordList(bizLearningRecord, page);
        if (Constants.SOCIETYCOURSE.toString().equals(bizLearningRecord.getCourseType())) {
            for (LearningRecordVO vo : voList.getRecords()) {
                String learningTime = "";
                if (vo != null) {
                    learningTime = BaseUtils.getTime(vo.getPdfTime());
                }
                vo.setLearningTime(learningTime);
            }
        }
        return voList;
    }

    @Override
    public List<PlaybackFileVO> selectPlaybackFileVOList(String courseId, String userId, int fileType) {
        return learningRecordMapper.selectPlaybackFileVOList(courseId, userId, fileType);
    }

    /**
     * 新增课程学习状态业务
     *
     * @param learningRecord 课程学习状态业务
     * @return 结果
     */
    @Override
    public int insertBizLearningRecord(LearningRecord learningRecord) {
        LearningRecordVO vo = learningRecordMapper.selectBizLearningRecordById(learningRecord.getCourseId(), learningRecord.getUserId());
        if (vo == null) {
            learningRecord.setStudyType(Constants.NOSTUDY);
            learningRecord.setYear(BaseUtils.getThisYear());
            return learningRecordMapper.insertBizLearningRecord(learningRecord);
        } else {
            return 1;
        }
    }

    /**
     * 修改课程学习状态业务
     *
     * @param learningRecord 课程学习状态业务
     * @return 结果
     */
    @Override
    public int updateBizLearningRecord(LearningRecord learningRecord) {
        return learningRecordMapper.updateBizLearningRecord(learningRecord);
    }

    @Override
    @Transactional
    public int ClearingLearningRecord(String courseId, String userId) {
        LearningRecord learningRecord = new LearningRecord();
        learningRecord.setCourseId(courseId);
        learningRecord.setUserId(userId);
        learningRecord.setStudyType(Constants.NOSTUDY);
        learningRecord.setYear(BaseUtils.getThisYear());
        learningRecord.setNoPassCount(Constants.NOSTUDY);
        learningRecordMapper.updateBizLearningRecord(learningRecord);
        learningRecordFileMapper.deleteBizLearningRecordFileById(courseId, userId);
        return 1;
    }

    /**
     * 批量删除课程学习状态业务
     *
     * @param courseIds 需要删除的课程学习状态业务ID
     * @return 结果
     */
    @Override
    public int deleteBizLearningRecordByIds(String[] courseIds, String userId) {
        return learningRecordMapper.deleteBizLearningRecordByIds(courseIds, userId);
    }

    /**
     * 删除课程学习状态业务信息
     *
     * @param courseId 课程学习状态业务ID
     * @return 结果
     */
    @Override
    public int deleteBizLearningRecordById(String courseId, String userId) {
        return learningRecordMapper.deleteBizLearningRecordById(courseId, userId);
    }
}
