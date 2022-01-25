package com.esmooc.legion.edu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.edu.entity.Prompt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 课程审核记录Mapper接口
 *
 * @author ruoyi
 * @date 2021-02-22
 */
@Mapper
public interface PromptMapper extends BaseMapper<Prompt> {
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
    IPage<Prompt> selectBizPromptList(@Param("prompt") Prompt prompt,@Param("page") Page page);

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
     * 删除课程审核记录
     *
     * @param id 课程审核记录ID
     * @return 结果
     */
    int deleteBizPromptById(Long id);

    /**
     * 批量删除课程审核记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizPromptByIds(Long[] ids);
}
