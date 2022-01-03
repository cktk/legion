package com.esmooc.legion.pacs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.pacs.entity.Patient;
import org.apache.ibatis.annotations.Mapper;


/** 
* @ClassName: PatientMapper
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}