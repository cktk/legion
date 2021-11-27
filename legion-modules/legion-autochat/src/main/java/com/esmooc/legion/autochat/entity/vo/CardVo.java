package com.esmooc.legion.autochat.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
public class CardVo<T> {

    String code = "knowledge";

    T data;
}
