package com.esmooc.legion.autochat.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
public class CardVo<T> {

    String code = "knowledge";

    T data;
}
