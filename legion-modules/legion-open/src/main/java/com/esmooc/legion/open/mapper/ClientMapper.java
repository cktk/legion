package com.esmooc.legion.open.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.open.entity.Client;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户端数据处理层
 *
 * @author DaiMao
 */
@Mapper
public interface ClientMapper extends BaseMapper<Client> {

}
