package com.esmooc.legion.pacs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.pacs.entity.Study;
import com.esmooc.legion.pacs.entity.dto.StudyDTO;
import com.esmooc.legion.pacs.entity.vo.StudiesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/** 
* @ClassName: StudyMapper
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
@Mapper
public interface StudyMapper extends BaseMapper<Study> {
    Page<StudyDTO> searchByPage(@Param("page") Page page, @Param("studiesVo") StudiesVo studiesVo);

}
