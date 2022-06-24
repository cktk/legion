package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esmooc.legion.core.common.constant.SystemConstant;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.mapper.SysDictMapper;
import com.esmooc.legion.core.entity.Dict;
import com.esmooc.legion.core.service.DictService;
/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月23日 11:27
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */

@Service
public class DictServiceImpl extends ServiceImpl<SysDictMapper, Dict> implements DictService {


    /**
     * 只查询父类 包括已禁用的
     *
     * @param type
     * @return
     */
    @Override
    public Dict findParentTypeAll(String type) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getType, type)
                .eq(Dict::isParent, true)
                .orderByDesc(Dict::getSortOrder);
        return this.getOne(queryWrapper);
    }

    /**
     * 只查询父类 包括已禁用的
     *
     * @param type
     * @return
     */
    @Override
    public List<Dict> findByTypeAll(String type) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getType, type)
                .eq(Dict::isParent, false)
                .orderByDesc(Dict::getSortOrder);
        return this.list(queryWrapper);
    }

    /**
     * 根据字典type查询子类 不包含已禁用的
     *
     * @param type
     * @return
     */
    @Override
    public List<Dict> findByType(String type) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getType, type)
                .eq(Dict::getStatus, SystemConstant.FLAG_Y)
                .eq(Dict::isParent, false)
                .orderByDesc(Dict::getSortOrder);
        return this.list(queryWrapper);
    }

    /**
     * 通过字典的唯一code 查询字典项
     *
     * @param code
     * @return
     */
    @Override
    public Dict findByCode(String code) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getCode, code)
                .eq(Dict::isParent,false)
                .eq(Dict::getStatus, SystemConstant.FLAG_Y);
        return this.getOne(queryWrapper);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Dict findByCodeAll(String code) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getCode, code)
                .eq(Dict::isParent,false);
        return this.getOne(queryWrapper);
    }

    @Override
    public Dict switchs(Long id, String status) {


        Dict dictData = this.getById(id);

        if (dictData == null) {
            throw new IllegalArgumentException("字典不存在");
        }
        if (SystemConstant.FLAG_Y.equals(dictData.isParent())) {
            //通过父类查询所有子类
            List<Dict> data = findByType(dictData.getType());
            if (SystemConstant.FLAG_N.equals(status)) {
                data.forEach(e -> e.setStatus(SystemConstant.FLAG_N));
            } else {
                //如果是启用呢
                throw new IllegalArgumentException("不允许批量启用");
            }
            if (this.updateBatchById(data)) {
                return dictData;
            }
            throw new IllegalArgumentException("禁用失败");
        }
        //子类
        if (SystemConstant.FLAG_Y.equals(status)) {
            //查找是否还有相同的启用了
            Dict oldDict = findByCode(dictData.getCode());
            if (oldDict==null) {
                dictData.setStatus(SystemConstant.FLAG_Y);
                 this.updateById(dictData);
                 return dictData;
            }

            if (oldDict.getId().equals(dictData.getId())) {
                //证明已经启用了
                return dictData;
            }
            throw new IllegalArgumentException("状态不对 请检查是否有两个相同的配置同时启用了");
        }
        dictData.setStatus(SystemConstant.FLAG_N);
         this.updateById(dictData);

         return dictData;

    }

    /**
     * @param key
     * @return
     */
    @Override
    public List<Dict> findByTitleOrTypeLike(String key) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(Dict::getType,key).or()
                .like(Dict::getCode,key).or()
                .like(Dict::getDescription,key).or()
                .like(Dict::getRemarks,key).or()
                .like(Dict::getLabel,key).or()
                .like(Dict::getValue,key).or()
                .like(Dict::getPinyin,key).last("limit  50");
        return this.list(queryWrapper);
    }

}
