package com.example.test;

import com.example.util.LongId;
import com.example.util.RandomUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :)
 *
 * @author MiaoOne
 * @since 2019/8/14 15:55
 */
public class RandomDemo {
    @Test
    public void test() {
        Set<Long> set = new HashSet<>();
        while (true) {
            int originSize = set.size();
            Long rand = RandomUtil.getRand();
            System.out.println(rand);
            set.add(rand);
            int afterSize = set.size();
            if (originSize == afterSize) {
                System.out.println("afterSize = " + afterSize);
                System.err.println(rand);
                return;
            }
        }
    }

    // 没有 maven jar
    @Test
    public void test1() {
        /*LongGenerator longGenerator = new LongGenerator(false, 1L);
        Set<Long> set = new HashSet<>();
        while (true) {
            int beforeSize = set.size();
            Long aLong = longGenerator.nextLongIdentifier();
            System.out.println("aLong = " + aLong);
            set.add(aLong);
            int afterSize = set.size();
            if (aLong == 100) {
                return;
            }
            if (beforeSize == afterSize) {
                System.out.println(aLong);
                return;
            }
        }*/
    }

    // nonononono
    @Test
    public void test2() {
        long id = LongId.getId();
        while (true) {
            System.out.println("id = " + id);
        }
    }

    @Test
    public void test3() {
        Set<Long> set = new HashSet<>();
        while (true) {
            int beforeSize = set.size();
            long l = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            System.out.println("l = " + l);
            set.add(l);
            int afterSize = set.size();
            if (beforeSize == afterSize) {
                System.out.println("l = " + l);
                return;
            }
        }
    }
}
