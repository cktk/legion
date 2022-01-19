package com.esmooc.legion.open.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.open.common.constant.Constants;
import com.esmooc.legion.open.common.utils.BaseUtils;
import com.esmooc.legion.open.common.utils.DateUtils;
import com.esmooc.legion.open.common.utils.FileUtils;
import com.esmooc.legion.open.entity.CourseFile;
import com.esmooc.legion.open.entity.vo.FileVO;
import com.esmooc.legion.open.mapper.CourseFileMapper;
import com.esmooc.legion.open.service.CourseFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 课程文件附件业务Service业务层处理
 *
 * @author sun
 * @date 2020-12-28
 */
@Service
public class CourseFileServiceImpl implements CourseFileService {
    @Autowired
    private CourseFileMapper courseFileMapper;

    /**
     * 查询课程文件附件业务
     *
     * @param id 课程文件附件业务ID
     * @return 课程文件附件业务
     */
    @Override
    public CourseFile selectBizCourseFileById(String id) {
        return courseFileMapper.selectBizCourseFileById(id);
    }

    /**
     * 查询课程文件附件业务列表
     *
     * @param courseFile 课程文件附件业务
     * @param page
     * @return 课程文件附件业务
     */
    @Override
    @Transactional
    public IPage<CourseFile> selectBizCourseFileList(CourseFile courseFile, Page page) {
        if (Constants.ISNOTDELETE.equals(courseFile.getDelFlag())) {
            /** 将页面假数据删除  0  delete */
            deleteBizCourseFileByCourseIdANDDelFlag(courseFile.getCourseId(), courseFile.getFileType());
        } else {
            courseFile.setDelFlag(null);
        }
        return courseFileMapper.selectBizCourseFileList(courseFile, page);
    }


    /**
     * 新增课程文件附件业务
     *
     * @param courseId 课程文件附件业务
     * @return 结果
     */
    @Override
    public String insertBizCourseFile(String courseId, long fileType, MultipartFile file, User user) {
        String id = BaseUtils.getUUID();
        FileVO vo = FileUtils.writeFile(file);
        CourseFile courseFile = new CourseFile();
        BeanUtils.copyProperties(vo, courseFile);
        courseFile.setId(id);
        courseFile.setCourseId(courseId);
        courseFile.setFileType(fileType);
        courseFile.setNumber(99 + "");
        courseFile.setCreateTime(DateUtils.getNowDate());
        courseFile.setDelFlag(Constants.ISDELETE);
        courseFileMapper.insertBizCourseFile(courseFile);
        return id;
    }

    @Override
    public int insertBizCourseFiles(String courseId, long fileType, MultipartFile[] files, User user) {
        for (int i = 0; i < files.length; i++) {
            this.insertBizCourseFile(courseId, fileType, files[i], user);
        }
        return files.length;
    }

    /**
     * 修改课程文件附件业务
     *
     * @param courseFile 课程文件附件业务
     * @return 结果
     */
    @Override
    public int updateBizCourseFile(CourseFile courseFile) {
        courseFile.setDelFlag(Constants.ISDELETE);
        return courseFileMapper.updateBizCourseFile(courseFile);
    }


    /**
     * 修改课程文件附件业务
     *
     * @param courseFileList 课程文件附件业务
     * @return 结果
     */
    @Override
    public int updateBizCourseFiles(List<CourseFile> courseFileList) {
        for (CourseFile courseFile :
                courseFileList) {
            courseFile.setDelFlag(Constants.ISNOTDELETE);
            courseFileMapper.updateBizCourseFile(courseFile);
        }
        return courseFileList.size();
    }

    /**
     * 批量删除课程文件附件业务
     *
     * @param ids 需要删除的课程文件附件业务ID
     * @return 结果
     */
    @Override
    public int deleteBizCourseFileByIds(String[] ids) {
        if (ids.length > 0) {
            courseFileMapper.deleteBizCourseFileByIds(ids);
        }
        return 1;
    }

    /**
     * 删除课程文件附件业务信息
     *
     * @param id 课程文件附件业务ID
     * @return 结果
     */
    @Override
    public int deleteBizCourseFileById(String id) {
        return courseFileMapper.deleteBizCourseFileById(id);
    }

    @Override
    public int deleteBizCourseFileByCourseIdANDDelFlag(String courseId, Long fileType) {
        List<String> fileNames = courseFileMapper.selectBizCourseFileNameById(courseId);
        FileUtils.deleteFiles(fileNames);
        return courseFileMapper.deleteBizCourseFileByCourseIdANDDelFlag(courseId, fileType, Constants.ISDELETE);
    }

    @Override
    public int updateBizCourseFilesNumber(List<CourseFile> courseFileList) {
        for (CourseFile courseFile :
                courseFileList) {
            courseFileMapper.updateBizCourseFilesNumber(courseFile);
        }
        return courseFileList.size();
    }
}
