package org.anonymous.test;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

/**
 * @author child
 * 2019/6/24 19:15
 */
public class Demo0 {
    @Test
    public void test() throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream is = classLoader.getResourceAsStream("D:\\Develop\\IDEA_Project\\newProject\\bean2map\\src\\test\\java\\org\\anonymous\\test\\Demo0.java");
        File file = File.createTempFile("demo", "txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
//            long l = is.transferTo(fos); //NPE
        }
    }

    @Test
    public void test1() {
        int[] arr = {};
        boolean array = arr.getClass().isArray();
        System.out.println("array = " + array);
        int length = Array.getLength(new int[1]);
        System.out.println("length = " + length);
    }
}
