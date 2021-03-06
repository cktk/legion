package com.esmooc.legion.file.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.file.entity.LegionFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;


/**
 * 文件管理数据处理层
 *
 * @author DaiMao
 */
@Mapper
public interface FileMapper extends BaseMapper<LegionFile> {
    /**
     * 通过categoryId删除
     *
     * @param categoryId
     */
    @Delete("delete from File f where f.category_id = #{categoryId}")
    void deleteByCategoryId(String categoryId);
}
