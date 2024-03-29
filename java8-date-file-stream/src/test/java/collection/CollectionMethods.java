package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static collection.HTMLColors.*;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2020/3/25 19:23
 */
public class CollectionMethods {
    public static void main(String[] args) {
        List<String> c =
                new ArrayList<>(LIST.subList(0, 4));
        c.add("ten");
        c.add("eleven");
        show(c);
        border();
        // Make an array from the List:
        Object[] array = c.toArray();
        // Make a String array from the List:
        String[] str = c.toArray(new String[0]);
        // Find max and min elements; this means
        // different things depending on the way
        // the Comparable interface is implemented:
        System.out.println(
                "Collections.max(c) = " + Collections.max(c));
        System.out.println(
                "Collections.min(c) = " + Collections.min(c));
        border();
        // Add a Collection to another Collection
        Collection<String> c2 =
                new ArrayList<>(LIST.subList(10, 14));
        c.addAll(c2);
        show(c);
        border();
        c.remove(LIST.get(0));
        show(c);
        border();
        // Remove all components that are
        // in the argument collection:
        c.removeAll(c2);
        show(c);
        border();
        c.addAll(c2);
        show(c);
        border();
        // Is an element in this Collection?
        String val = LIST.get(3);
        System.out.println(
                "c.contains(" + val + ") = " + c.contains(val));
        // Is a Collection in this Collection?
        System.out.println(
                "c.containsAll(c2) = " + c.containsAll(c2));
        Collection<String> c3 =
                c.subList(3, 5);
        // Keep all the elements that are in both
        // c2 and c3 (an intersection of sets):
        c2.retainAll(c3);
        show(c2);
        // Throw away all the elements
        // in c2 that also appear in c3:
        c2.removeAll(c3);
        System.out.println(
                "c2.isEmpty() = " + c2.isEmpty());
        border();
        // Functional operation:
        c = new ArrayList<>(LIST);
        c.removeIf(s -> !s.startsWith("P"));
        c.removeIf(s -> s.startsWith("Pale"));
        c.forEach(System.out::println);
        c.clear(); // Remove all elements
        System.out.println("after c.clear():" + c);
    }
}
