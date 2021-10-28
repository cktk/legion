package com.esmooc.legion.autochat.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
public class GuessVo<T> {

    String image = "./img/guess.png";

    String type = "recommend";

    List<T> list;
}
