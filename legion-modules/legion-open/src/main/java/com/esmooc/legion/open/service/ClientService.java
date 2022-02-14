package com.esmooc.legion.open.service;

import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.open.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 客户端接口
 * @author DaiMao
 */
public interface ClientService extends LegionBaseService<Client, String> {

    /**
     * 多条件分页获取
     * @param client
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Client> findByCondition(Client client, SearchVo searchVo, Pageable pageable);

}
