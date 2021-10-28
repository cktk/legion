package com.esmooc.legion.system.controller;

import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.system.entity.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： LeiYanFu
 * @date： 2020/5/30 12:46
 * @About：
 */
@RestController
@RequestMapping("/legion/system")
public class SystemController {
    @GetMapping("/info")
    @SystemLog(description = "获取系统信息", type = LogType.OPERATION)
    public Result delAllByIds() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {

        }
        return ResultUtil.data(server);
    }
}
