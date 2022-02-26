package com.esmooc.legion.core.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.WordTree;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.dao.StopWordDao;
import com.esmooc.legion.core.entity.StopWord;
import com.esmooc.legion.core.service.StopWordService;
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
import java.util.Date;
import java.util.List;

/**
 * 禁用词管理接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class StopWordServiceImpl implements StopWordService {

    private static WordTree wordTree;
    @Autowired
    private StopWordDao stopWordDao;

    @Override
    public StopWordDao getRepository() {
        return stopWordDao;
    }

    public WordTree getInstance() {

        if (wordTree == null) {
            // 初始加载数据
            wordTree = new WordTree();
            stopWordDao.findAll().forEach(e -> wordTree.addWord(e.getTitle()));
        }
        return wordTree;
    }

    @Override
    public Page<StopWord> findByCondition(StopWord stopWord, SearchVo searchVo, Pageable pageable) {

        return stopWordDao.findAll(new Specification<StopWord>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<StopWord> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> titleField = root.get("title");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<>();

                if (StrUtil.isNotBlank(stopWord.getTitle())) {
                    list.add(cb.like(titleField, "%" + stopWord.getTitle() + "%"));
                }

                //TODO 加上默认时间后冲突的BUG 创建时间
//                if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
//                    Date start = DateUtil.parse(searchVo.getStartDate());
//                    Date end = DateUtil.parse(searchVo.getEndDate());
//                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
//                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

}
