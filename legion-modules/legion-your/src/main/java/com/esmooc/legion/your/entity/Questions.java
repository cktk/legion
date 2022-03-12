package com.esmooc.legion.your.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 *
 * @Date: 2022/03/11/ 14:18
 * @Description: 
 */
@ApiModel(value="questions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "questions")
public class Questions {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long id;

    @TableField(value = "title")
    @ApiModelProperty(value="")
    private String title;

    @TableField(value = "parent_id")
    @ApiModelProperty(value="")
    private Long parentId;

    /**
     * 类型  1 选择  2多选 3判断 4简答 5图片 
     */
    @TableField(value = "questions_type")
    @ApiModelProperty(value="类型  1 选择  2多选 3判断 4简答 5图片 ")
    private String questionsType;

    /**
     * 是否是复合型题型 比如xxxxx  问题 1 根据题干回答下列若干个问题
     */
    @TableField(value = "combination")
    @ApiModelProperty(value="是否是复合型题型 比如xxxxx  问题 1 根据题干回答下列若干个问题")
    private String combination;
}