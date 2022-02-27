package com.esmooc.legion.activiti.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.activiti.dao.ActBusinessDao;
import com.esmooc.legion.activiti.entity.ActBusiness;
import com.esmooc.legion.activiti.service.ActBusinessService;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.SearchVo;
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
 * 业务申请接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class ActBusinessServiceImpl implements ActBusinessService {

    @Autowired
    private ActBusinessDao actBusinessDao;



    @Override
    public ActBusinessDao getRepository() {
        return actBusinessDao;
    }

    @Override
    public Page<ActBusiness> findByCondition(ActBusiness actBusiness, SearchVo searchVo, Pageable pageable) {

        return actBusinessDao.findAll(new Specification<ActBusiness>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ActBusiness> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> titleField = root.get("title");
                Path<Integer> statusField = root.get("status");
                Path<Integer> resultField = root.get("result");
                Path<Date> createTimeField = root.get("createTime");
                Path<String> userIdField = root.get("userId");
                Path<String> procDefIdField = root.get("procDefId");

                List<Predicate> list = new ArrayList<Predicate>();

                // 模糊搜素
                if (StrUtil.isNotBlank(actBusiness.getTitle())) {
                    list.add(cb.like(titleField, '%' + actBusiness.getTitle() + '%'));
                }

                // 状态
                if (actBusiness.getStatus() != null) {
                    list.add(cb.equal(statusField, actBusiness.getStatus()));
                }
                // 结果
                if (actBusiness.getResult() != null) {
                    list.add(cb.equal(resultField, actBusiness.getResult()));
                }

                // 创建时间
                if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                // 用户
                list.add(cb.equal(userIdField,  SecurityUtil.getUser().getId()));

                list.add(cb.notEqual(procDefIdField, ""));

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public List<ActBusiness> findByProcDefId(String procDefId) {

        return actBusinessDao.findByProcDefId(procDefId);
    }
}
