package com.esmooc.legion.file.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.file.entity.FileCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件分类数据处理层
 *
 * @author Exrick
 */
@Mapper
public interface FileCategoryMapper extends BaseMapper<FileCategory> {

}
