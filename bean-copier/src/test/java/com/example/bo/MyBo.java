package com.example.bo;

import com.example.common.BaseVo;
import lombok.*;

import java.util.Date;

/**
 * @author MiaoOne
 * 2019/8/1 21:43
 * target bean
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
// @Accessors(chain = true) // 该注解加了就无法实现赋值: BeanCopier/BeanMap 都无法正常赋值
public class MyBo extends BaseVo implements Cloneable { // 继承的属性 可以 实现赋值(使用 @ToString(callSuper = true))
    private String name;

    public MyBo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public MyBo(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    @Override
    public MyBo clone() throws CloneNotSupportedException {
        return new MyBo(id, name, date);
//        return (MyBo) super.clone();
    }

    private String password;
    private Date date;
}
