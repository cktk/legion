package com.esmooc.legion.pacs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.pacs.entity.Study;
import com.esmooc.legion.pacs.entity.dto.StudyDTO;
import com.esmooc.legion.pacs.entity.vo.StudiesVo;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: StudyService
 * @Description:
 * @date 2021年12月21日15点54分
 **/
public interface StudyService extends IService<Study>{


    Page<StudyDTO> searchByPage(Page page, StudiesVo studiesVo);

}
