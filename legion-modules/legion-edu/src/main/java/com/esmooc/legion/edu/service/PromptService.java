package com.esmooc.legion.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.edu.entity.Prompt;

/**
 * 课程审核记录Service接口
 *
 * @author ruoyi
 * @date 2021-02-22
 */
public interface PromptService  extends IService<Prompt> {
    /**
     * 查询课程审核记录
     *
     * @param id 课程审核记录ID
     * @return 课程审核记录
     */
     Prompt selectBizPromptById(Long id);

    /**
     * 查询课程审核记录列表
     *
     * @param prompt 课程审核记录
     * @param page
     * @return 课程审核记录集合
     */
     IPage<Prompt> selectBizPromptList(Prompt prompt, Page page);

    /**
     * 新增课程审核记录
     *
     * @param prompt 课程审核记录
     * @return 结果
     */
     int insertBizPrompt(Prompt prompt);

    /**
     * 修改课程审核记录
     *
     * @param prompt 课程审核记录
     * @return 结果
     */
     int updateBizPrompt(Prompt prompt);

    /**
     * 批量删除课程审核记录
     *
     * @param ids 需要删除的课程审核记录ID
     * @return 结果
     */
     int deleteBizPromptByIds(Long[] ids);

    /**
     * 删除课程审核记录信息
     *
     * @param id 课程审核记录ID
     * @return 结果
     */
     int deleteBizPromptById(Long id);
}
