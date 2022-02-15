package com.esmooc.legion.your.entity.cervicalGynecological;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GynecologicalDto {
    @NotBlank(message = "检查机构不能为空")
    private String checkOrg;

    @NotBlank(message = "检查人不能为空")
    private String checkUser;

    @NotNull(message = "日期不能为空")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;
    private int id;
    @NotNull(message = "用户id不能为空")
    private int userId;
    private String cervix;// 子宫颈
    private String clinical;// 临床
    private String enclosure;// 附件
    private String inspect;// 检查
    private String secretion;// 分泌物
    private String uterus;// 子宫
    private String vagina;// 阴道
    private String vulva;
    private String fjdx1;
    private String fjdx2;
    private String fjxz;
    private String fjwz;
    private String zgjdx1;
    private String zgjzwdx1;
    private String zgjzwdx2;
    private String zgjzwwz;
    private String zgjzwxz;
    private String wqi;
    private String yqita;
    private String zgjqita;
    private String zgqita;
    private String fjqita;
    private String jcqita;
    private String zdqita;
    private String upload;
}
