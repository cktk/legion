package com.esmooc.legion.autochat.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TextVo {

    String text;
}
