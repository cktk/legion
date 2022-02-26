package com.esmooc.legion.quartz.serviceimpl;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.quartz.dao.QuartzJobDao;
import com.esmooc.legion.quartz.entity.QuartzJob;
import com.esmooc.legion.quartz.service.QuartzJobService;
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
 * 定时任务接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private QuartzJobDao quartzJobDao;

    @Override
    public QuartzJobDao getRepository() {
        return quartzJobDao;
    }

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {

        return quartzJobDao.findByJobClassName(jobClassName);
    }

    @Override
    public Page<QuartzJob> findByCondition(String key, Pageable pageable) {

        return quartzJobDao.findAll(new Specification<QuartzJob>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<QuartzJob> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> jobClassNameField = root.get("jobClassName");
                Path<String> parameterField = root.get("parameter");
                Path<String> descriptionField = root.get("description");

                List<Predicate> list = new ArrayList<>();

                // 模糊搜素
                if (StrUtil.isNotBlank(key)) {
                    Predicate p1 = cb.like(jobClassNameField, '%' + key + '%');
                    Predicate p2 = cb.like(parameterField, '%' + key + '%');
                    Predicate p3 = cb.like(descriptionField, '%' + key + '%');
                    list.add(cb.or(p1, p2, p3));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }
}
