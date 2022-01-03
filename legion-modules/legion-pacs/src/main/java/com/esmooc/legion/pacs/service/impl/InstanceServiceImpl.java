package com.esmooc.legion.pacs.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.pacs.mapper.InstanceMapper;
import com.esmooc.legion.pacs.entity.Instance;
import com.esmooc.legion.pacs.service.InstanceService;

/** 
* @ClassName: InstanceServiceImpl
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
@Service
public class InstanceServiceImpl extends ServiceImpl<InstanceMapper, Instance> implements InstanceService{

}
