/*
package org.anonymous.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

*/
/**
 * @author child
 * 2019/6/24 18:39
 *//*

public class Demo {
    public static void main(String[] args) {
        //jdk 10+ : 局部变量类型推断(只能是方法内部的局部变量, 不能是 形参, 成员变量)
        var list = new ArrayList<String>(){{
            add("123");
            add("24345");
        }};
        list.forEach(System.out::println);

        boolean blank = " ".isBlank(); //空:
        System.out.println("blank = " + blank);

        boolean empty = " ".isEmpty(); //长度为 0 返回 true
        System.out.println("empty = " + empty);

        //----------------------------
        */
/**
         * static <E> List<E> of(E... elements) {
         *   switch (elements.length) { // implicit null check of elements
         *     case 0:
         *         return ImmutableCollections.emptyList();
         *     case 1:
         *         return new ImmutableCollections.List12<>(elements[0]);
         *     case 2:
         *         return new ImmutableCollections.List12<>(elements[0], elements[1]);
         *     default:
         *         return new ImmutableCollections.ListN<>(elements);
         *   }
         * }
         * static <E> List<E> copyOf(Collection<? extends E> coll) {
         *     return ImmutableCollections.listCopy(coll);
         * }
         * static <E> List<E> listCopy(Collection<? extends E> coll) {
         *     if (coll instanceof AbstractImmutableList && coll.getClass() != SubList.class) {
         *         return (List<E>)coll;
         *     } else {
         *         return (List<E>)List.of(coll.toArray());
         *     }
         * }
         *//*

        //----------------------------

        var list1 = List.of("java", "python", "php");
        var copy1 = List.copyOf(list1);
        System.out.println(list1 == copy1); //true

        var list2 = new ArrayList<String>();
        var copy2 = List.copyOf(list2);
        System.out.println(list2 == copy2); //false

        //-----------------------------

        */
/**
         *  public static<T> Stream<T> ofNullable(T t) {
         *      return t == null ? Stream.empty()
         *                       : StreamSupport.stream(new Streams.StreamBuilderImpl<>(t), false);
         *  }
         *//*

        long count = Stream.ofNullable(null).count();
        System.out.println("count = " + count); //count = 0

        //获取满足条件的: 获取 小于 3 的
        List<Integer> collect = Stream.of(1, 3, 5, 7)
                .takeWhile(n -> n < 3)
                .collect(Collectors.toList()); //collect = [1]
        System.out.println("collect = " + collect);

        //排除满足条件的: 排除小于 3 的
        Set<Integer> set = Stream.of(1, 3, 6, 6, 9)
                .dropWhile(n -> n < 3)
                .collect(Collectors.toSet()); //set = [3, 6, 9]
        System.out.println("set = " + set);
    }

}
*/
