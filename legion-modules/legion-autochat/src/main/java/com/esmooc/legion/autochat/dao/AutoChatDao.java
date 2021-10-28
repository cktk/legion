 package com.esmooc.legion.autochat.dao;

import com.esmooc.legion.core.base.LegionBaseDao;
import com.esmooc.legion.autochat.entity.AutoChat;

import java.util.List;

 /**
  * 问答助手客服数据处理层
  * @author Daimao
  */
 public interface AutoChatDao extends LegionBaseDao<AutoChat, String> {

     /**
      * 完全匹配
      * @param title
      * @return
      */
     List<AutoChat> findByTitle(String title);
 }