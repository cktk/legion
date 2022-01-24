package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.entity.CourseFile;
import com.esmooc.legion.edu.entity.LearningRecord;
import com.esmooc.legion.edu.entity.LearningRecordFile;
import com.esmooc.legion.edu.entity.vo.LearningRecordVO;
import com.esmooc.legion.edu.entity.vo.RankingVO;
import com.esmooc.legion.edu.mapper.CourseFileMapper;
import com.esmooc.legion.edu.mapper.LearningRecordFileMapper;
import com.esmooc.legion.edu.mapper.LearningRecordMapper;
import com.esmooc.legion.edu.service.LearningRecordFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 课程附件学习状态业务Service业务层处理
 *
 * @author sun
 * @date 2020-12-28
 */
@Service
public class LearningRecordFileServiceImpl extends ServiceImpl<LearningRecordFileMapper,LearningRecordFile >  implements LearningRecordFileService {
    @Autowired
    private LearningRecordFileMapper learningRecordFileMapper;

    @Autowired
    private LearningRecordMapper learningRecordMapper;

    @Autowired
    private CourseFileMapper courseFileMapper;

    /**
     * 查询课程附件学习状态业务
     *
     * @param learningRecordFile 课程附件学习状态业务ID
     * @return 课程附件学习状态业务
     */
    @Override
    public LearningRecordFile selectBizLearningRecordFileById(LearningRecordFile learningRecordFile) {
        return learningRecordFileMapper.selectBizLearningRecordFileById(learningRecordFile);
    }

    /**
     * 查询课程附件学习状态业务列表
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @param page
     * @return 课程附件学习状态业务
     */
    @Override
    public IPage<LearningRecordFile> selectBizLearningRecordFileList(LearningRecordFile learningRecordFile, Page page) {
        return learningRecordFileMapper.selectBizLearningRecordFileList(learningRecordFile,page);
    }

    /**
     * 新增课程附件学习状态业务
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @return 结果
     */
    @Override
    public int insertBizLearningRecordFile(LearningRecordFile learningRecordFile) {
        return learningRecordFileMapper.insertBizLearningRecordFile(learningRecordFile);
    }

    @Override
    public int insertVideoLearningRecord(LearningRecordFile bizLearningRecordFile) throws Exception {
        //获取课程学习状态
        LearningRecordVO vo = learningRecordMapper.selectBizLearningRecordById(bizLearningRecordFile.getCourseId(), bizLearningRecordFile.getUserId());
        //未学习  直接入库  不存在  重复数据
        if (Constants.NOSTUDY == vo.getStudyType() && bizLearningRecordFile.getType() == Constants.STUDY) {
            bizLearningRecordFile.setStartTime(new Date());
            learningRecordFileMapper.insertBizLearningRecordFile(bizLearningRecordFile);
            //将课程变为  学习中
            LearningRecord learningRecord = new LearningRecord();
            BeanUtils.copyProperties(bizLearningRecordFile, learningRecord);
            learningRecord.setStudyType(Constants.STUDY);
            learningRecordMapper.updateBizLearningRecord(learningRecord);
        }
        //学习中  不能直接入库  存在重复数据
        else {
            //判断当前文件学习转态是否入库
            LearningRecordFile learningRecordFile = learningRecordFileMapper.selectBizLearningRecordFileById(bizLearningRecordFile);
            if (learningRecordFile == null && bizLearningRecordFile.getType() == Constants.STUDY) {
                bizLearningRecordFile.setStartTime(new Date());
                learningRecordFileMapper.insertBizLearningRecordFile(bizLearningRecordFile);
            } else if (bizLearningRecordFile.getType() == Constants.ENDSTUDY) {
                bizLearningRecordFile.setEndTime(new Date());
                learningRecordFileMapper.updateBizLearningRecordFile(bizLearningRecordFile);
            } else { /** 没有业务逻辑 */}
        }
        if (Constants.STUDY == vo.getStudyType()) {
            //查询课程下的文件个数
            CourseFile courseFile = new CourseFile();
            courseFile.setCourseId(bizLearningRecordFile.getCourseId());
            courseFile.setFileType(Constants.VIDEO.longValue());
            int fileSize = (int) courseFileMapper.selectBizCourseFileList(courseFile, null).getSize();
            int size = learningRecordFileMapper.selectBizLearningRecordFileSize(bizLearningRecordFile);
            // 单片文件数和学完的文件数符合 将课程变为  学习完
            if (size == fileSize) {
                LearningRecord learningRecord = new LearningRecord();
                BeanUtils.copyProperties(bizLearningRecordFile, learningRecord);
                learningRecord.setStudyType(Constants.ENDSTUDY);
                learningRecordMapper.updateBizLearningRecord(learningRecord);
                return Constants.ENDSTUDY;
            } else {
                return Constants.STUDY;
            }
        } else {
            return Constants.STUDY;
        }
    }

    @Override
    @Transactional
    public int insertPdfLearningRecord(LearningRecordFile bizLearningRecordFile) {
        //获取课程学习状态
        LearningRecordVO vo = learningRecordMapper.selectBizLearningRecordById(bizLearningRecordFile.getCourseId(), bizLearningRecordFile.getUserId());
        //未学习  直接入库  不存在  重复数据
        if (Constants.NOSTUDY == vo.getStudyType()) {
            bizLearningRecordFile.setStartTime(new Date());
            bizLearningRecordFile.setEndTime(new Date());
            learningRecordFileMapper.insertBizLearningRecordFile(bizLearningRecordFile);
        }
        //学习中 学习完  不能直接入库  存在重复数据
        else {
            //判断当前文件学习转态是否入库
            LearningRecordFile learningRecordFile = learningRecordFileMapper.selectBizLearningRecordFileById(bizLearningRecordFile);

            if (learningRecordFile == null) {
                bizLearningRecordFile.setStartTime(new Date());
                bizLearningRecordFile.setEndTime(new Date());
                learningRecordFileMapper.insertBizLearningRecordFile(bizLearningRecordFile);
            } else {
                bizLearningRecordFile.setEndTime(new Date());
                learningRecordFileMapper.updateBizLearningRecordFile(bizLearningRecordFile);
            }
            long time = learningRecordFileMapper.selectBizLearningRecordFileTime(bizLearningRecordFile);
            time = time + bizLearningRecordFile.getTime().longValue();
            bizLearningRecordFile.setTime(time);
            learningRecordFileMapper.updateBizLearningRecordFileTime(bizLearningRecordFile);
        }
        if (Constants.NOSTUDY == vo.getStudyType() || Constants.STUDY == vo.getStudyType()) {
            //查询课程下的文件个数
            CourseFile courseFile = new CourseFile();
            courseFile.setCourseId(bizLearningRecordFile.getCourseId());
            courseFile.setFileType(Constants.PDF.longValue());
            int fileSize = (int) courseFileMapper.selectBizCourseFileList(courseFile, null).getSize();
            int size = learningRecordFileMapper.selectBizLearningRecordFileSize(bizLearningRecordFile);
            // 单片文件数和学完的文件数符合 将课程变为  学习完 否则 为学习中
            if (size == fileSize) {
                LearningRecord learningRecord = new LearningRecord();
                BeanUtils.copyProperties(bizLearningRecordFile, learningRecord);
                learningRecord.setStudyType(Constants.ENDSTUDY);
                learningRecordMapper.updateBizLearningRecord(learningRecord);
            } else {
                LearningRecord learningRecord = new LearningRecord();
                BeanUtils.copyProperties(bizLearningRecordFile, learningRecord);
                learningRecord.setStudyType(Constants.STUDY);
                learningRecordMapper.updateBizLearningRecord(learningRecord);
            }
        }
        return 1;
    }

    /**
     * 修改课程附件学习状态业务
     *
     * @param learningRecordFile 课程附件学习状态业务
     * @return 结果
     */
    @Override
    public int updateBizLearningRecordFile(LearningRecordFile learningRecordFile) {
        return learningRecordFileMapper.updateBizLearningRecordFile(learningRecordFile);
    }

    /**
     * 批量删除课程附件学习状态业务
     *
     * @param courseIds 需要删除的课程附件学习状态业务ID
     * @return 结果
     */
    @Override
    public int deleteBizLearningRecordFileByIds(String[] courseIds, String userId) {
        return learningRecordFileMapper.deleteBizLearningRecordFileByIds(courseIds, userId);
    }

    /**
     * 删除课程附件学习状态业务信息
     *
     * @param courseId 课程附件学习状态业务ID
     * @return 结果
     */
    @Override
    public int deleteBizLearningRecordFileById(String courseId, String userId) {
        return learningRecordFileMapper.deleteBizLearningRecordFileById(courseId, userId);
    }

    @Override
    public List<RankingVO> selectVideoStudentRanking() {
        return learningRecordFileMapper.selectVideoStudentRanking();
    }

    @Override
    public List<RankingVO> selectOpenStudentRanking() {
        List<RankingVO> list = learningRecordFileMapper.selectOpenStudentRanking();
        if (list.size() > 0) {
            for (RankingVO vo :
                    list) {
                String learningTime = "0秒";
                if (vo != null) {
                    if (vo.getPeriod() != null && vo.getPeriod() != "") {
                        learningTime = BaseUtils.getTime((long) Double.parseDouble(vo.getPeriod()));
                    }
                }
                vo.setPeriod(learningTime);
            }
        }
        return list;
    }

    @Override
    public List<RankingVO> selectVideoUnitRanking() {
        List<RankingVO> list = learningRecordFileMapper.selectVideoUnitRanking();
        if (list.size() > 0) {
            for (RankingVO vo :
                    list) {
                String learningTime = "0";
                if (vo != null) {
                    if (vo.getPeriod() != null && vo.getPeriod() != "") {
                        learningTime = vo.getPeriod();
                    }
                }
                vo.setPeriod(learningTime);
            }
        }
        return list;
    }

    @Override
    public List<RankingVO> selectOpenUnitRanking() {
        List<RankingVO> list = learningRecordFileMapper.selectOpenUnitRanking();
        if (list.size() > 0) {
            for (RankingVO vo :
                    list) {
                String learningTime = "0秒";
                if (vo != null) {
                    if (vo.getPeriod() != null && vo.getPeriod() != "") {
                        learningTime = BaseUtils.getTime((long) Double.parseDouble(vo.getPeriod()));
                    }
                }
                vo.setPeriod(learningTime);
            }
        }
        return list;
    }
}
