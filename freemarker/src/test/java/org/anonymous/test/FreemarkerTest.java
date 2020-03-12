package org.anonymous.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author child
 * 2019/6/25 11:01
 */
public class FreemarkerTest {

    public static void main(String[] args) throws IOException, TemplateException {
        //创建一个配置对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板所在的目录(文件夹)
        configuration.setDirectoryForTemplateLoading(
                //相对路径: 以当前项目为根目录
                new File("freemarker-demo\\src\\main\\resources"));
        configuration.setDefaultEncoding("utf-8");
        //获取模板对象: 模板文件名
        Template template = configuration.getTemplate("freemarker.ftl");

        //创建数据模型: 可以是对象,也可以是 map

        @SuppressWarnings("serial")
        HashMap<String, String> goods1 = new HashMap<String, String>(){{
            put("name", "葡萄");
            put("price", "123");
        }};
        @SuppressWarnings("serial")
        HashMap<String, String> goods2 = new HashMap<String, String>(){{
            put("name", "香蕉");
            put("price", "233");
        }};

        @SuppressWarnings("serial")
        ArrayList<Map<String, String>> goodsList = new ArrayList<Map<String, String>>(){{
            add(goods1);
            add(goods2);
        }};

        /**
         * warning: 这里传递的 key 值必须与模板中定义的 interpolation(插入文字, 插值)
         * 一一对应, 参数名完全一致(不能多,少,不同名等)
         * 否则会报错!!
         */
        @SuppressWarnings("serial")
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("name", "jack");
            put("message", "welcome to freemarker !");
            put("success", "false");
            put("goodsList", goodsList);
            put("date", new Date());
            put("number", 3254356465L); //去掉数字分隔符: 默认三位数字一个 逗号分隔, 只有字符串会原样显示
        }};

        //输出流
        FileWriter fw = new FileWriter("freemarker-demo/src/main/resources/test.html");
        //输出
        template.process(map, fw);

        //释放资源
        fw.close();
    }
}
