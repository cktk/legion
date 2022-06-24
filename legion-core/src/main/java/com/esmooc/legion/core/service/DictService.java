package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.Dict;

import java.util.List;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月23日 11:27
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */

public interface DictService extends IService<Dict> {

    /**
     * 只查询父类 包括已禁用的
     * @param type
     * @return
     */
    Dict findParentTypeAll(String type);



    /**
     * 只查询子类 包括已禁用的
     * @param type
     * @return
     */
    List<Dict> findByTypeAll(String type);

    /**
     * 根据字典type查询子类 不包含已禁用的
     * @param dictType
     * @return
     */
    List<Dict> findByType(String dictType);

    /**
     * 通过字典的唯一code 查询字典项
     * @param typeCode
     * @return
     */
    Dict findByCode(String typeCode);

    Dict findByCodeAll(String typeCode);


    /**
     * 启用禁用 如果是父类  那么也禁用所有子类
     * 如果是子类 那就只禁用自己  且启用只能启用一个 不能重复
     * @param id
     * @param status
     * @return
     */
    Dict switchs(Long id, String status);

    List<Dict>  findByTitleOrTypeLike(String key);
}
