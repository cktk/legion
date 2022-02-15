package com.esmooc.legion.your.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.your.entity.UserUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author 呆猫
 *
 * @Date: 2022/02/10/ 15:38
 * @Description:
 */
@Mapper
@DS("screening2")
public interface UserUserMapper extends BaseMapper<UserUser> {

    /**
     * 只查询一条用户 按照添加时间排序
     * @param idCard
     * @return
     */
    @Select("select * from user_user where idcard = #{idCard}   ORDER BY ADDTIME desc limit 1")
    UserUser getByIdCardMax(@Param("idCard") String idCard);



    @Select(" select current_val from sequence   where seq_name = 'seq_user_card'")
    String getCurrentVal();

    @Update(" update sequence set current_val = current_val + 1 where seq_name = 'seq_user_card' ")
    void updateCurrentVal();


    IPage<UserUser> getGynecologicalUser(@Param("startTime") String startTime, @Param("endTime") String endTime,
                                         @Param("input") String input, @Param("diagnose") String diagnose, @Param("page") Page page);

    IPage<UserUser> getTctUser(@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("input") String input,@Param("tct") String tct,@Param("page")  Page page);

    IPage<UserUser> getMuBaAndBreastUserNew(@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("input") String input, @Param("page")  Page page);

    IPage<UserUser> getMuBaUserNew(@Param("startTime")String startTime,@Param("endTime") String endTime,@Param("input")  String input,@Param("mubale")  String mubale,@Param("colorle")   String colorle,@Param("page")   Page page);

    IPage<UserUser> getBreastUserNew(@Param("startTime")String startTime, @Param("endTime")  String endTime,@Param("input")  String input,@Param("mubale")  String mubale, @Param("colorle") String colorle,@Param("page")  Page page);


}
