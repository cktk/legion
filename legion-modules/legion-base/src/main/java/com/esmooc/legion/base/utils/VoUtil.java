package com.esmooc.legion.base.utils;

import com.esmooc.legion.base.vo.MenuVo;
import com.esmooc.legion.core.common.utils.BeanHelper;
import com.esmooc.legion.core.entity.Permission;

/**
 * @author DaiMao
 */
public class VoUtil {
    public static MenuVo permissionToMenuVo(Permission p) {
        MenuVo menuVo = null;
        try {
            menuVo = BeanHelper.copyProperties(p, MenuVo.class);
        } catch (Exception e) {
            System.out.println("报错了");
        }
        return menuVo;
    }
}
