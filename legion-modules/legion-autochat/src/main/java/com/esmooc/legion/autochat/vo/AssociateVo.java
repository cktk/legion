package com.esmooc.legion.autochat.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
public class AssociateVo<T> {

    List<T> list;

    String keyword;
}
