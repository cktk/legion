package com.esmooc.legion.core.config.datascope;

import cn.hutool.core.collection.CollUtil;

import java.util.List;


/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about : 默认data scope 判断处理器
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
public class DefaultDatascopeHandle implements DataScopeHandle {


    /**
     * 计算用户数据权限
     *
     * @param deptList 部门ID，如果为空表示没有任何数据权限。
     * @return 返回true表示无需进行数据过滤处理，返回false表示需要进行数据过滤
     */
    @Override
    public Boolean calcScope(List<String> deptList) {
        if (CollUtil.isEmpty(deptList)) {
            return true;
        }
        return false;
    }

}
