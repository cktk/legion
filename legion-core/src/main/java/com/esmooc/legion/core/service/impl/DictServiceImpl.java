package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.constant.SystemConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.entity.Dict;
import com.esmooc.legion.core.mapper.SysDictMapper;
import com.esmooc.legion.core.service.DictService;
import org.springframework.stereotype.Service;

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
    @Override
    public IPage<Dict> findByTypeAll(String type,PageVo pageVo) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getType, type)
                .eq(Dict::getStatus, SystemConstant.FLAG_Y)
                .eq(Dict::isParent, false)
                .orderByDesc(Dict::getSortOrder);
        return this.page(PageUtil.initMpPage(pageVo),queryWrapper);
    }


    /**
     * 通过字典的唯一code 查询字典项
     *
     * @param value
     * @return
     */
    @Override
    public Dict findByValue(String value) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getValue, value)
                .eq(Dict::getStatus, SystemConstant.FLAG_Y);
        return this.getOne(queryWrapper);
    }

    /**
     * @param value
     * @return
     */
    @Override
    public List<Dict> findByValueAll(String value) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getValue, value)
                .eq(Dict::isParent,false);
        return this.list(queryWrapper);
    }

    @Override
    public Dict switchs(Long id, boolean status) {


        Dict dictData = this.getById(id);

        if (dictData == null) {
            throw new IllegalArgumentException("字典不存在");
        }
        if (dictData.isParent()) {
            //通过父类查询所有子类
            List<Dict> data = findByType(dictData.getType());
            if (!status) {
                data.forEach(e -> e.setStatus(false));
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
            Dict oldDict = findByValue(dictData.getValue());
            if (oldDict==null) {
                dictData.setStatus(true);
                 this.updateById(dictData);
                 return dictData;
            }

            if (oldDict.getId().equals(dictData.getId())) {
                //证明已经启用了
                return dictData;
            }
            throw new IllegalArgumentException("状态不对 请检查是否有两个相同的配置同时启用了");
        }
        dictData.setStatus(false);
         this.updateById(dictData);

         return dictData;

    }

    /**
     * @param key
     * @return
     */
    @Override
    public IPage<Dict> search(PageVo page, String key) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(Dict::getType,key).or()
                .like(Dict::getCode,key).or()
                .like(Dict::getDescription,key).or()
                .like(Dict::getRemarks,key).or()
                .like(Dict::getLabel,key).or()
                .like(Dict::getValue,key).or()
                .like(Dict::getPinyin,key);
        return this.page(PageUtil.initMpPage(page),queryWrapper);
    }

    /**
     * @param type
     * @param key
     * @return 通过type 模糊查询下面的所有类型
     */
    @Override
    public List<Dict> search(String type, String key) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dict::getType,type)
                .eq(Dict::isParent,false)
                .like(Dict::getCode,key).or()
                .like(Dict::getDescription,key).or()
                .like(Dict::getRemarks,key).or()
                .like(Dict::getLabel,key).or()
                .like(Dict::getValue,key).or()
                .like(Dict::getPinyin,key);
        return this.list(queryWrapper);
    }
}
