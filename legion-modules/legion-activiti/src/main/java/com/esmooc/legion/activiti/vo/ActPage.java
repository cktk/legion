package com.esmooc.legion.activiti.vo;

import lombok.Data;

import java.util.List;

/**
 * @author DaiMao
 */
@Data
public class ActPage<T> {

    List<T> content;

    Long totalElements;
}
