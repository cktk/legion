package com.esmooc.legion.pacs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.pacs.entity.Patient;
import com.esmooc.legion.pacs.mapper.PatientMapper;
import com.esmooc.legion.pacs.service.PatientService;
import org.springframework.stereotype.Service;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: PatientServiceImpl
 * @Description:
 * @date 2021年12月21日15点54分
 **/
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService{

}
