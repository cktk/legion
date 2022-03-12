package com.esmooc.legion.your.server.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.your.entity.Questions;
import com.esmooc.legion.your.mapper.QuestionsMapper;
import com.esmooc.legion.your.server.QuestionsService;
/**
 * @Author 呆猫
 *
 * @Date: 2022/03/11/ 14:18
 * @Description: 
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsService{

}
