package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zqj
 * @Create: 2021-11-16 13:44
 * @Description: 实体类
 */
@Data
@TableName("t_data_dict")
@AllArgsConstructor
@NoArgsConstructor
public class DictionariesEntity {

    /**
     * id
     */
    @TableField(value = "id")
    private String id;
    /**
     * 代码
     */
    @TableField(value = "`CODE`")
    private String code;

    /**
     * 值
     */
    @TableField(value = "DICT_NAME")
    private String dictName;

    /**
     * 标准类型(ZZ和ZL打头为自定义，其它全为国标)
     */
    @TableField(value = "TYPE")
    private String type;

    /**
     * 标准(由标准类型+代码组合而成)
     */
    @TableField(value = "DICT_CODE")
    private String dictCode;

    /**
     * 说明
     */
    @TableField(value = "DES")
    private String des;

}
