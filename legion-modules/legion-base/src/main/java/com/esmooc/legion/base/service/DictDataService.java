package com.esmooc.legion.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.base.entity.DictData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 字典数据接口
 * @author Daimao
 */
public interface DictDataService extends IService<DictData> {


    /**
     * 通过dictId获取启用字典 已排序
     * @param dictId
     * @return
     */
    List<DictData> findByDictId(String dictId);

    /**
     * 通过dictId删除
     * @param dictId
     */
    void deleteByDictId(String dictId);
}
