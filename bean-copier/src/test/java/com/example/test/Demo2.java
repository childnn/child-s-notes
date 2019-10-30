package com.example.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MiaoOne
 * 2019/8/6 16:36
 * 多个集合排列组合元素
 * 待测试
 */
@Deprecated
public class Demo2 {

    public static void main(String[] args) {

        List<Object> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
      /*  List<Object> l2 = new ArrayList<>();
        l2.add("a");
        l2.add("b");

        List<Object> l3 = new ArrayList<>();
        l3.add("+");
        l3.add("-");*/

        List<List<Object>> ls = new ArrayList<>();
        ls.add(l1);
//        ls.add(l2);
//        ls.add(l3);
        List<Object> list = cartesianList(ls);
        System.out.println(list.size());
        for (Object object : list) {
            System.out.println(object);
        }

    }

    private static List<Object> cartesianList(List<List<Object>> al0) {

        List<Object> a0 = al0.get(0);// l1
        List<Object> result = new ArrayList<>();// 组合的结果
        for (int i = 1; i < al0.size(); i++) {
            List<Object> a1 = al0.get(i);
            List<Object> temp = new ArrayList<>();
            // 每次先计算两个集合的笛卡尔积，然后用其结果再与下一个计算
            for (Object o1 : a0) {
                for (Object o : a1) {
                    List<Object> cut = new ArrayList<>();

                    if (o1 instanceof ArrayList) {
                        cut.addAll((ArrayList) o1);
                    } else {
                        cut.add(o1);
                    }
                    if (o instanceof ArrayList) {
                        cut.addAll((ArrayList) o);
                    } else {
                        cut.add(o);
                    }
                    temp.add(cut);
                }
            }
            a0 = temp;
            if (i == al0.size() - 1) {
                result = temp;
            }
        }
        return result;
    }

}
