package com.esmooc.legion.your.entity.cervicalGynecological;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="cervical_gynecological_context")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cervical_gynecological_context")
public class CervicalGynecological {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private Integer userId;

    @TableField(value = "vulva")
    @ApiModelProperty(value="")
    private String vulva;

    @TableField(value = "vagina")
    @ApiModelProperty(value="")
    private String vagina;

    @TableField(value = "uterus")
    @ApiModelProperty(value="")
    private String uterus;

    @TableField(value = "secretion")
    @ApiModelProperty(value="")
    private String secretion;

    @TableField(value = "inspect")
    @ApiModelProperty(value="")
    private String inspect;

    @TableField(value = "enclosure")
    @ApiModelProperty(value="")
    private String enclosure;

    @TableField(value = "clinical")
    @ApiModelProperty(value="")
    private String clinical;

    @TableField(value = "cervix")
    @ApiModelProperty(value="")
    private String cervix;

    @TableField(value = "fjdx1")
    @ApiModelProperty(value="")
    private String fjdx1;

    @TableField(value = "fjdx2")
    @ApiModelProperty(value="")
    private String fjdx2;

    @TableField(value = "fjxz")
    @ApiModelProperty(value="")
    private String fjxz;

    @TableField(value = "fjwz")
    @ApiModelProperty(value="")
    private String fjwz;

    @TableField(value = "zgjdx1")
    @ApiModelProperty(value="")
    private String zgjdx1;

    @TableField(value = "zgjzwdx1")
    @ApiModelProperty(value="")
    private String zgjzwdx1;

    @TableField(value = "zgjzwdx2")
    @ApiModelProperty(value="")
    private String zgjzwdx2;

    @TableField(value = "zgjzwxz")
    @ApiModelProperty(value="")
    private String zgjzwxz;

    @TableField(value = "zgjzwwz")
    @ApiModelProperty(value="")
    private String zgjzwwz;

    @TableField(value = "wqi")
    @ApiModelProperty(value="")
    private String wqi;

    @TableField(value = "yqita")
    @ApiModelProperty(value="")
    private String yqita;

    @TableField(value = "fqita")
    @ApiModelProperty(value="")
    private String fqita;

    @TableField(value = "zgjqita")
    @ApiModelProperty(value="")
    private String zgjqita;

    @TableField(value = "zgqita")
    @ApiModelProperty(value="")
    private String zgqita;

    @TableField(value = "fjqita")
    @ApiModelProperty(value="")
    private String fjqita;

    @TableField(value = "jcqita")
    @ApiModelProperty(value="")
    private String jcqita;

    @TableField(value = "zdqita")
    @ApiModelProperty(value="")
    private String zdqita;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value="")
    private Date addtime;
}