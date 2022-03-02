package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.base.entity.DatasourceConf;
import com.esmooc.legion.base.service.DatasourceConfService;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Author 呆猫
 * @Date: 2022/03/02/ 10:43
 * @Description:
 */

@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "数据源管理接口")
@RequestMapping("/legion/datasource")
public class DatasourceController {

    private final DatasourceConfService datasourceConfService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param datasourceConf 数据源表
     * @return
     */
    @GetMapping("/page")
    public Result<IPage<List<DatasourceConf>>> getSysDatasourceConfPage(Page page, DatasourceConf datasourceConf) {
        return ResultUtil.data(datasourceConfService.page(page, Wrappers.query(datasourceConf)));
    }

    /**
     * 查询全部数据源
     * @return
     */
    @GetMapping("/list")
    public Result<List<DatasourceConf>> list() {
        return ResultUtil.data(datasourceConfService.list());
    }

    /**
     * 通过id查询数据源表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public Result<DatasourceConf> getById(@PathVariable("id") Long id) {
        return ResultUtil.data(datasourceConfService.getById(id));
    }

    /**
     * 新增数据源表
     * @param datasourceConf 数据源表
     * @return R
     */
    @SystemLog(type = LogType.OPERATION,description = "新增数据源表")
    @PostMapping
    public Result<Boolean> save(@RequestBody DatasourceConf datasourceConf) {
        return ResultUtil.data(datasourceConfService.saveDsByEnc(datasourceConf));
    }

    /**
     * 修改数据源表
     * @param conf 数据源表
     * @return R
     */
    @SystemLog(type = LogType.OPERATION,description = "修改数据源表")
    @PutMapping
    public Result<Boolean> updateById(@RequestBody DatasourceConf conf) {
        return ResultUtil.data(datasourceConfService.updateDsByEnc(conf));
    }

    /**
     * 通过id删除数据源表
     * @param id id
     * @return R
     */
    @SystemLog(description = "删除数据源表",type = LogType.OPERATION)
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Long id) {
        return ResultUtil.data(datasourceConfService.removeByDsId(id));
    }

}
