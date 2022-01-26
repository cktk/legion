package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.common.utils.DateUtils;
import com.esmooc.legion.edu.common.utils.FileUtils;
import com.esmooc.legion.edu.entity.CourseFile;
import com.esmooc.legion.edu.entity.vo.FileVO;
import com.esmooc.legion.edu.mapper.CourseFileMapper;
import com.esmooc.legion.edu.service.CourseFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 课程文件附件业务Service业务层处理
 *
 * @author sun
 * @date 2020-12-28
 */
@Service
public class CourseFileServiceImpl extends ServiceImpl<CourseFileMapper, CourseFile> implements CourseFileService {

}
