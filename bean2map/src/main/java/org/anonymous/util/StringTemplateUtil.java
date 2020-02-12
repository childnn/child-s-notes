package org.anonymous.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author child
 * 2019/6/24 12:24
 */
public class StringTemplateUtil {
    public static final String DEF_REGEX = "\\{(.+?)\\}";

    public static String render(String template, Map<String, String> data) {
        return render(template, data, DEF_REGEX);
    }

    private static String render(String template, Map<String, String> data, String regex) {
        /**
         * 区别:
         * @see StringUtils#isEmpty(java.lang.String): 空格字符串返回 false  " "
         * @see StringUtils#isBlank(java.lang.String): 空格字符串返回 true
         * 相同: null, 空字符串 返回 true
         */
        if (StringUtils.isBlank(template)) {
            return "";
        }
        if (StringUtils.isBlank(regex)) {
            return template;
        }
        if (MapUtils.isEmpty(data)) {
            return template;
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            String name = matcher.group(1); //键名
            String value = data.get(name);
            if (value == null) {
                value = "";
            }
            matcher.appendReplacement(sb, value);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String template = "您提现{borrowAmount}元至尾号{tailNo}的请求失败, 您可以重新提交提款申请..";
        @SuppressWarnings("serial")
        Map<String, String> data = new HashMap<String, String>() {{
            put("borrowAmount", "1000.00");
            put("tailNo", "1234");
        }};
        String render = render(template, data);
        //render = 您提现1000.00元至尾号1234的请求失败, 您可以重新提交提款申请..
        System.out.println("render = " + render);

    }
}
