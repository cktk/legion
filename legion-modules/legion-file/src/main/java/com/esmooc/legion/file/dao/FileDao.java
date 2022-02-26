package com.esmooc.legion.file.dao;


import com.esmooc.legion.core.base.LegionBaseDao;
import com.esmooc.legion.file.entity.File;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 文件管理数据处理层
 *
 * @author DaiMao
 */
public interface FileDao extends LegionBaseDao<File, String> {
    /**
     * 通过categoryId删除
     *
     * @param categoryId
     */
    @Modifying
    @Query("delete from File f where f.categoryId = ?1")
    void deleteByCategoryId(String categoryId);
}
