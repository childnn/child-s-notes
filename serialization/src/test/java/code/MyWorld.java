package code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2020/4/6 15:51
 */
public class MyWorld {

    public static void main(String[] args) {
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        System.out.println("animals: " + animals);
        try (
                ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
                ObjectOutputStream o1 = new ObjectOutputStream(buf1)
        ) {
            o1.writeObject(animals);
            o1.writeObject(animals); // Write a 2nd set
            // Write to a different stream:
            try (
                    ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
                    ObjectOutputStream o2 = new ObjectOutputStream(buf2)
            ) {
                o2.writeObject(animals);
                // Now get them back:
                try (
                        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
                        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()))
                ) {
                    List animals1 = (List) in1.readObject(),
                            animals2 = (List) in1.readObject(),
                            animals3 = (List) in2.readObject();
                    System.out.println("animals1: " + animals1);
                    System.out.println("animals2: " + animals2);
                    System.out.println("animals3: " + animals3);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class House implements Serializable {
}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() +
                "], " + preferredHouse + "\n";
    }

}