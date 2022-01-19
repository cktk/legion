package com.esmooc.legion.pacs.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.pacs.entity.Study;
import com.esmooc.legion.pacs.entity.dto.StudyDTO;
import com.esmooc.legion.pacs.entity.vo.StudiesVo;
import com.esmooc.legion.pacs.mapper.StudyMapper;
import com.esmooc.legion.pacs.service.StudyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: StudyServiceImpl
 * @Description:
 * @date 2021年12月21日15点54分
 **/
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class StudyServiceImpl extends ServiceImpl<StudyMapper, Study> implements StudyService{

    StudyMapper studyMapper;
    @Override
    public Page<StudyDTO> searchByPage(Page page, StudiesVo studiesVo) {
        return studyMapper.searchByPage(page,studiesVo);
    }
}
