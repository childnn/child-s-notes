package test.emoji;

import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.Arrays;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :)
 *
 * @author MiaoOne
 * @since 2019/9/24 17:27
 */
public class EmojiTest {
    @Test
    public void test() {
        String s = EmojiParser.parseToUnicode("😀秋香");
        System.out.println(s);
        String s1 = EmojiParser.parseToAliases("😀");
        System.out.println("s1 = " + s1);
    }

    @Test
    public void test1() {
        byte[] bytes = Base64.encodeBase64("😀秋香".getBytes());
        String s1 = Arrays.toString(bytes);
        System.out.println("s1 = " + s1);
        String s = new String(bytes);
        System.out.println("s = " + s);

        byte[] bytes1 = Base64.decodeBase64(s.getBytes());
        String s2 = new String(bytes1);
        System.out.println("s2 = " + s2);
    }
}
