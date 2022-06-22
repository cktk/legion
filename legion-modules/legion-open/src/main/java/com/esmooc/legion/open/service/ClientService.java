package com.esmooc.legion.open.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.open.entity.Client;



/**
 * 客户端接口
 *
 * @author DaiMao
 */
public interface ClientService extends IService<Client> {

    /**
     * 多条件分页获取
     *
     * @param client
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<Client> findByCondition(Client client, SearchVo searchVo, PageVo pageable);

}
