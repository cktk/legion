package com.esmooc.legion.file.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.file.entity.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件管理数据处理层
 * @author Daimao
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

}
