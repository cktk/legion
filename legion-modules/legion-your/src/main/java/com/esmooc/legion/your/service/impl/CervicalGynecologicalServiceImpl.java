package com.esmooc.legion.your.service.impl;


import com.esmooc.legion.your.entity.cervicalGynecological.GynecologicalDto;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.your.entity.cervicalGynecological.CervicalGynecological;
import com.esmooc.legion.your.mapper.CervicalGynecologicalMapper;
import com.esmooc.legion.your.service.CervicalGynecologicalService;
@Service

public class CervicalGynecologicalServiceImpl extends ServiceImpl<CervicalGynecologicalMapper, CervicalGynecological> implements CervicalGynecologicalService {

    @Override
    public GynecologicalDto getAllByUserId(int userId) {
        return baseMapper.getAllByUserId(userId);
    }
}
