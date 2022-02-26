package com.esmooc.legion.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.base.dao.DictDataDao;
import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.base.service.DictDataService;
import com.esmooc.legion.core.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    private DictDataDao dictDataDao;

    @Override
    public DictDataDao getRepository() {
        return dictDataDao;
    }

    @Override
    public Page<DictData> findByCondition(DictData dictData, Pageable pageable) {

        return dictDataDao.findAll(new Specification<DictData>() {
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

        return dictDataDao.findByDictIdAndStatusOrderBySortOrder(dictId, CommonConstant.STATUS_NORMAL);
    }

    @Override
    public void deleteByDictId(String dictId) {

        dictDataDao.deleteByDictId(dictId);
    }


    @Override
    public DictData findByTypeCode(String typeCode) {
        return dictDataDao.findByTypeCodeAndStatusOrderBySortOrder(typeCode, CommonConstant.STATUS_NORMAL);
    }

    @Override
    public List<DictData> findByType(String type) {
        return dictDataDao.findByTypeAndStatusOrderBySortOrder(type, CommonConstant.STATUS_NORMAL);

    }
}
