package com.example.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author MiaoOne
 * 2019/8/1 21:41
 * source bean
 */
@Data
@Accessors(chain = true)
public class MyVo {
    private Integer id;
    private String name;
    private String date;
}
