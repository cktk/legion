package com.esmooc.legion.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.base.dao.DictDataMapper;
import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.base.service.DictDataService;
import com.esmooc.legion.core.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class DictDataServiceImpl implements DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public DictDataMapper getRepository() {
        return dictDataMapper;
    }

    @Override
    public Page<DictData> findByCondition(DictData dictData, Pageable pageable) {

        return dictDataMapper.findAll(new Specification<DictData>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<DictData> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> titleField = root.get("title");
                Path<Integer> statusField = root.get("status");
                Path<String> dictIdField = root.get("dictId");

                List<Predicate> list = new ArrayList<>();

                // 模糊搜素
                if (StrUtil.isNotBlank(dictData.getTitle())) {
                    list.add(cb.like(titleField, '%' + dictData.getTitle() + '%'));
                }

                // 状态
                if (dictData.getStatus() != null) {
                    list.add(cb.equal(statusField, dictData.getStatus()));
                }

                // 所属字典
                if (StrUtil.isNotBlank(dictData.getDictId())) {
                    list.add(cb.equal(dictIdField, dictData.getDictId()));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
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
