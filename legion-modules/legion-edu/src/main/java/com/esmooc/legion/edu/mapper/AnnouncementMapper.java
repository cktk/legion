package com.esmooc.legion.edu.mapper;

import com.esmooc.legion.edu.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    /**
     * 查询通知公告
     *
     * @param id 通知公告ID
     * @return 通知公告
     */
    Announcement selectBizAnnouncementById(String id);

    /**
     * 查询通知公告列表
     *
     * @param announcement 通知公告
     * @return 通知公告集合
     */
    List<Announcement> selectBizAnnouncementList(Announcement announcement);

    /**
     * 新增通知公告
     *
     * @param announcement 通知公告
     * @return 结果
     */
    int insertBizAnnouncement(Announcement announcement);

    /**
     * 修改通知公告
     *
     * @param announcement 通知公告
     * @return 结果
     */
    int updateBizAnnouncement(Announcement announcement);

    /**
     * 删除通知公告
     *
     * @param id 通知公告ID
     * @return 结果
     */
    int deleteBizAnnouncementById(String id);

    /**
     * 批量删除通知公告
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizAnnouncementByIds(String[] ids);

    /**
     * 新增发布范围
     *
     * @param uuid
     * @param id
     * @param userId
     */
    void insertBizAnnouncementUser(@Param("id") String uuid,
                                   @Param("aid") String id,
                                   @Param("uid") String userId);

    String selectReleaseScopeById(String id);

    void deleteReleaseScopeById(String id);

    List<Announcement> selectUserList(@Param("userId") String userId);
}
