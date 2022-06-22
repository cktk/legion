package com.esmooc.legion.file.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.file.entity.FileCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文件分类数据处理层
 *
 * @author Exrick
 */
@Mapper
public interface FileCategoryMapper extends BaseMapper<FileCategory> {

    /**
     * 通过父id和创建人获取
     *
     * @param parentId
     * @param createBy
     * @return
     */
    @Select("select * from  t_file_category where parent_id =#{ParentId} and create_by =#{CreateBy} order by sort_order")
    List<FileCategory> findByParentIdAndCreateByOrderBySortOrder(@Param("ParentId") String parentId,@Param("CreateBy") String createBy);

    /**
     * 通过名称和创建人模糊搜索
     *
     * @param title
     * @param createBy
     * @return
     */
    @Select("select * from  t_file_category where title like CONCAT('%',#{title},'%')  and create_by =#{createBy} order by sort_order")
    List<FileCategory> findByTitleLikeAndCreateByOrderBySortOrder(@Param("title") String title,@Param("createBy") String createBy);
}
