package notes;

import org.junit.Test;

import java.util.*;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2019/11/16 16:02
 */
public class Utils {
    @Test
    public void test$() {
        String arr = "1, 2, 3,,,";
        String[] split = arr.split(",");
        System.out.println(Arrays.toString(split));
    }

    @Test
    public void list() {
        @SuppressWarnings("serial")
        List<String> list = new ArrayList<String>() {{
            add("1");
            add("2");
        }};
        // 1.
        /*Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (Objects.equals("1", item)) {
                iterator.remove();
            }
        }*/
        for (String item : list) {
            if (Objects.equals("1", item)) {
                list.remove(item);
            }
        }
    }


}
