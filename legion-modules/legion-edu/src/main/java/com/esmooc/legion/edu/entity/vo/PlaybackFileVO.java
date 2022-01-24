package com.esmooc.legion.edu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 播放文件列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaybackFileVO {

    private String id;
    private String fileName;
    private String name;
    private String learningState;

}
