<html>
<head>
    <title>demo</title>
    <meta charset="utf-8">
</head>

<body>

<#--指令2: include, 引入其他模板-->
<#include "include.ftl">
<#--我是freemarker注释-->
<!--我是html注释, 我会原样输出..-->

${name}, 你好. ${message}
<br/>
<#--指令1: assign 直接在页面赋值, 获取 插值-->
<#assign contact="rose">
联系人:${contact}

<br/>
<#--指令3: if-->
<#--这里双等号,单等号都可以-->
<#if success="true">
    登录成功!
    <#else>
    登录失败!
</#if>

<hr/>
<#--指令4: 循环-->
<#list goodsList as goods>
    <#--索引默认从 0 开始: 获取索引 对象_index-->
    ${goods_index + 1} 商品名称: ${goods.name}, 商品价格:${goods.price} <br/>
</#list>
<#--指令6: 内建函数1, 获取集合大小: size-->
商品数量: ${goodsList?size} <br/>

<#--指令5: 对象数据-->
<#assign test={"mobile": 123344, "address": "湖北武汉"}/>
电话: ${test.mobile} 地址: ${test.address}

<br/>
<#--指令7: 内建函数2, 字符串转对象: eval-->
<#assign text='{"mobile": "123344", "address": "湖北武汉"}'>
<#assign data=text?eval>

字符串转对象: ${data.mobile}&nbsp; ${data.address}
<hr/>

<#--指令8: 日期时间转换-->
当前日期: ${date?date}<br/>
当前时间: ${date?time}<br/>
当前日期+时间: ${date?datetime}<br/>
日期格式化: ${date?string("yyyy年MM月")}

<hr/>

<#--指令9: 数字转字符串-->
没有去掉数字分隔符: ${number}<br/>
去掉数字分隔符: ${number?c}

<br/>
<#--指令12: 关系运算符-->
关系运算符:
<#--这里必须加括号, 以区分 尖括号与 大于号-->
<#if (number > 100)>
    number 大于 100
</#if>
<br/>
<#if number gt 100>
    number greater than 100
</#if>

<hr/>

<#--指令10: 双问好, 判断变量是否存在-->
<#if a??>
    a 变量存在
    <#else>
    a 变量不存在
</#if>
<br/>
<#--指令11: 惊叹号, 相当于三元运算符-->
${aaa!"aaa没有被赋值"}

</body>
</html>