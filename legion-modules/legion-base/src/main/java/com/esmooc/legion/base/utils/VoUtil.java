package com.esmooc.legion.base.utils;

import com.esmooc.legion.base.vo.MenuVo;
import com.esmooc.legion.core.entity.Permission;
import cn.hutool.core.bean.BeanUtil;

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
