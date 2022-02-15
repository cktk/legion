package com.esmooc.legion.your.service;

import com.esmooc.legion.your.entity.cervicalGynecological.CervicalGynecological;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.your.entity.cervicalGynecological.GynecologicalDto;

public interface CervicalGynecologicalService extends IService<CervicalGynecological>{

     GynecologicalDto getAllByUserId(int userId);
}
