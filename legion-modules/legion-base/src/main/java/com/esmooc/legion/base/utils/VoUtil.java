package com.esmooc.legion.base.utils;

import cn.hutool.core.bean.BeanUtil;
import com.esmooc.legion.base.entity.vo.MenuVo;
import com.esmooc.legion.core.entity.Permission;

/**
 * @author Daimao
 */
public class VoUtil {

    public static MenuVo permissionToMenuVo(Permission p) {

        MenuVo menuVo = new MenuVo();
        BeanUtil.copyProperties(p, menuVo);
        return menuVo;
    }
}
