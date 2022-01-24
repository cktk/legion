package com.esmooc.legion.edu.entity.vo;


import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO extends BaseEntity {

    /**
     * 资料名称(同文件名称)
     */
    String name;
    /**
     * 文件名称
     */
    String fileName;

}
