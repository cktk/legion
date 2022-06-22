package com.esmooc.legion.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.base.mapper.DictDataMapper;
import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.base.service.DictDataService;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典数据接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class DictDataServiceImpl  extends ServiceImpl<DictDataMapper, DictData>  implements DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;


    @Override
    public IPage<DictData> findByCondition(DictData dictData, PageVo pageable) {
        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StrUtil.isNotBlank(dictData.getTitle()),DictData::getTitle ,dictData.getTitle() )
                .eq(dictData.getStatus()!=null,DictData::getStatus ,dictData.getStatus() )
                .eq(StrUtil.isNotBlank(dictData.getDictId()),DictData::getDictId ,dictData.getDictId() );
        return this.page(PageUtil.initMpPage(pageable),queryWrapper);
    }

    @Override
    public List<DictData> findByDictId(String dictId) {

        return dictDataMapper.findByDictIdAndStatusOrderBySortOrder(dictId, CommonConstant.STATUS_NORMAL);
    }

    @Override
    public void deleteByDictId(String dictId) {

        dictDataMapper.deleteByDictId(dictId);
    }


    @Override
    public DictData findByTypeCode(String typeCode) {
        return dictDataMapper.findByTypeCodeAndStatusOrderBySortOrder(typeCode, CommonConstant.STATUS_NORMAL);
    }

    @Override
    public List<DictData> findByType(String type) {
        return dictDataMapper.findByTypeAndStatusOrderBySortOrder(type, CommonConstant.STATUS_NORMAL);

    }
}
