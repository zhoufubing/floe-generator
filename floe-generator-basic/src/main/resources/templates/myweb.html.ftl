<!DOCTYPE html>
<html>
    <head>
        <title>FLOE官网</title>
    </head>
    <body>
    <h1>欢迎来到FLOE官网</h1>
    <u1>
        <#--循环渲染导航条-->
        <#list menuItems as item>
        <li><a href="${item.url}">${item.label}</a></li>
        </#list>
    </u1>
<#--底部版权信息（注释部分，不会被输出）-->
    <footer>
        ${currentYear}FLOE官网，All rights reserved.
    </footer>
    </body>
</html>