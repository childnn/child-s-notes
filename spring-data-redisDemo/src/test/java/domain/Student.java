package domain;

import java.io.Serializable;

/**
 * @author child
 * 2019/6/15 15:47
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 2265312704715085654L;
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
