package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.esmooc.legion.edu.entity.Prompt;
import com.esmooc.legion.edu.mapper.PromptMapper;
import com.esmooc.legion.edu.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程审核记录Service业务层处理
 *
 * @author ruoyi
 * @date 2021-02-22
 */
@Service
public class PromptServiceImpl implements PromptService {
    @Autowired
    private PromptMapper promptMapper;

    /**
     * 查询课程审核记录
     *
     * @param id 课程审核记录ID
     * @return 课程审核记录
     */
    @Override
    public Prompt selectBizPromptById(Long id) {
        return promptMapper.selectBizPromptById(id);
    }

    /**
     * 查询课程审核记录列表
     *
     * @param prompt 课程审核记录
     * @param page
     * @return 课程审核记录
     */
    @Override
    public IPage<Prompt> selectBizPromptList(Prompt prompt, Page page) {
        return promptMapper.selectBizPromptList(prompt,page);
    }

    /**
     * 新增课程审核记录
     *
     * @param prompt 课程审核记录
     * @return 结果
     */
    @Override
    public int insertBizPrompt(Prompt prompt) {
        return promptMapper.insertBizPrompt(prompt);
    }

    /**
     * 修改课程审核记录
     *
     * @param prompt 课程审核记录
     * @return 结果
     */
    @Override
    public int updateBizPrompt(Prompt prompt) {
        return promptMapper.updateBizPrompt(prompt);
    }

    /**
     * 批量删除课程审核记录
     *
     * @param ids 需要删除的课程审核记录ID
     * @return 结果
     */
    @Override
    public int deleteBizPromptByIds(Long[] ids) {
        return promptMapper.deleteBizPromptByIds(ids);
    }

    /**
     * 删除课程审核记录信息
     *
     * @param id 课程审核记录ID
     * @return 结果
     */
    @Override
    public int deleteBizPromptById(Long id) {
        return promptMapper.deleteBizPromptById(id);
    }
}
