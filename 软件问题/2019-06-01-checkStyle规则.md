---
title: checkStyle分割
date: 2019-02-20 03:33:00
tags: 
- SpringBoot
category: 
- SpringBoot
description: checkStyle分割
---

<!-- 

https://raw.githubusercontent.com/HealerJean/HealerJean.github.io/master/blogImages/
　　首行缩进

<font  clalss="healerColor" color="red" size="5" >     </font>

<font  clalss="healerSize"  size="5" >     </font>
-->




## 前言

#### [博主github](https://github.com/HealerJean)
#### [博主个人博客http://blog.healerjean.com](http://HealerJean.github.io)    



### 1、alibaba



![1560506516385](D:\study\HealerJean.github.io\blogImages\1560506516385.png)



#### 1.1、编码规范扫描





![1560507113634](D:\study\HealerJean.github.io\blogImages\1560507113634.png)

#### 1.2、扫描观察

![1560507167206](D:\study\HealerJean.github.io\blogImages\1560507167206.png)



#### 1.3、错误级别`Blocker` 、`Critical `、`Major`

1、Blocker: 即系统无法执行、崩溃或严重资源不足、应用模块无法启动或异常退出、无法测试、造成系统不稳定。

```
严重花屏
内存泄漏
用户数据丢失或破坏
系统崩溃/死机/冻结
模块无法启动或异常退出
严重的数值计算错误
功能设计与需求严重不符
其它导致无法测试的错误, 如服务器500错误
```

2、Critical：即影响系统功能或操作，主要功能存在严重缺陷，但不会影响到系统稳定性。

```
功能未实现
功能错误
系统刷新错误
数据通讯错误
轻微的数值计算错误
影响功能及界面的错误字或拼写错误
安全性问题
```

3、Major：即界面、性能缺陷、兼容性。

```
操作界面错误（包括数据窗口内列名定义、含义是否一致）
边界条件下错误
提示信息错误（包括未给出信息、信息提示错误等）
长时间操作无进度提示
系统未优化（性能问题）
光标跳转设置不好，鼠标（光标）定位错误
兼容性问题
```





Minor/Trivial:即易用性及建议性问题。






### 2、checkstyle

![1560507877211](D:\study\HealerJean.github.io\blogImages\1560507877211.png)





```xml
<?xml version="1.0"?>
<!DOCTYPE module PUBLIC
        "-//Puppy Crawl//DTD Check Configuration 1.3//EN"
        "http://www.puppycrawl.com/dtds/configuration_1_3.dtd">

<module name="Checker">
    <!--
        If you set the basedir property below, then all reported file
        names will be relative to the specified directory. See
        http://checkstyle.sourceforge.net/5.x/config.html#Checker
        <property name="basedir" value="${basedir}"/>
    -->
    <!-- 检查每个包中是否有java注释文件，默认有package-info.java -->
    <!-- <module name="JavadocPackage"/> -->
    <!-- 检查文件是否以一个空行结束 -->
    <!-- <module name="NewlineAtEndOfFile"/> -->

    <!-- 检查property文件中是否有相同的key -->
    <module name="Translation"/>
    <!-- 文件长度不超过1500行 -->
    <module name="FileLength">
        <property name="max" value="1500"/>
    </module>

    <!-- 检查文件中是否含有'\t' -->
    <module name="FileTabCharacter"/>

    <!-- Miscellaneous other checks. -->
    <module name="RegexpSingleline">
        <property name="format" value="\s+$"/>
        <property name="minimum" value="0"/>
        <property name="maximum" value="0"/>
        <property name="message" value="Line has trailing spaces."/>
    </module>

    <!-- 每个java文件一个语法树 -->
    <module name="TreeWalker">
        <!-- 注释检查 -->
        <!-- 检查方法和构造函数的javadoc -->
        <!--<module name="JavadocMethod">-->
            <!--<property name="tokens" value="METHOD_DEF" />-->
        <!--</module>-->
        <!-- 检查类和接口的javadoc。默认不检查author和version tags -->
        <!--<module name="JavadocType"/>-->
        <!-- 检查变量的javadoc -->
        <!--<module name="JavadocVariable"/>-->
        <!-- 检查javadoc的格式 -->
        <!--<module name="JavadocStyle">-->
            <!--<property name="checkFirstSentence" value="false"/>-->
        <!--</module>-->

        <!-- 检查TODO:注释 -->
        <module name="TodoComment"/>

        <!-- 命名检查 -->
        <!-- 局部的final变量，包括catch中的参数的检查 -->
        <module name="LocalFinalVariableName" />
        <!-- 局部的非final型的变量，包括catch中的参数的检查 -->
        <module name="LocalVariableName" />
        <!-- 包名的检查（只允许小写字母），默认^[a-z]+(\.[a-zA-Z_][a-zA-Z_0-9_]*)*$ -->
        <module name="PackageName">
            <property name="format" value="^[a-z]+(\.[a-z][a-z0-9]*)*$" />
            <message key="name.invalidPattern" value="包名 ''{0}'' 要符合 ''{1}''格式."/>
        </module>
        <!-- 仅仅是static型的变量（不包括static final型）的检查 -->
        <module name="StaticVariableName" />
        <!-- Class或Interface名检查，默认^[A-Z][a-zA-Z0-9]*$-->
        <module name="TypeName">
            <property name="severity" value="warning"/>
            <message key="name.invalidPattern" value="名称 ''{0}'' 要符合 ''{1}''格式."/>
        </module>
        <!-- 非static型变量的检查 -->
        <module name="MemberName" />
        <!-- 方法名的检查 -->
        <module name="MethodName" />
        <!-- 方法的参数名 -->
        <module name="ParameterName " />
        <!-- 常量名的检查（只允许大写），默认^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$ -->
        <module name="ConstantName" />

        <!-- 定义检查 -->
        <!-- 检查数组类型定义的样式 -->
        <module name="ArrayTypeStyle"/>
        <!-- 检查方法名、构造函数、catch块的参数是否是final的 -->
        <!-- <module name="FinalParameters"/> -->
        <!-- 检查long型定义是否有大写的“L” -->
        <module name="UpperEll"/>


        <!-- Checks for Headers                                -->
        <!-- See http://checkstyle.sf.net/config_header.html   -->
        <!-- <module name="Header">                            -->
        <!-- The follow property value demonstrates the ability     -->
        <!-- to have access to ANT properties. In this case it uses -->
        <!-- the ${basedir} property to allow Checkstyle to be run  -->
        <!-- from any directory within a project. See property      -->
        <!-- expansion,                                             -->
        <!-- http://checkstyle.sf.net/config.html#properties        -->
        <!-- <property                                              -->
        <!--     name="headerFile"                                  -->
        <!--     value="${basedir}/java.header"/>                   -->
        <!-- </module> -->

        <!-- Following interprets the header file as regular expressions. -->
        <!-- <module name="RegexpHeader"/>                                -->


        <!-- import检查-->
        <!-- 避免使用* -->
        <!-- <module name="AvoidStarImport"/> -->
        <!-- 检查是否从非法的包中导入了类 -->
        <module name="IllegalImport"/>
        <!-- 检查是否导入了多余的包 -->
        <module name="RedundantImport"/>
        <!-- 没用的import检查，比如：1.没有被用到2.重复的3.import java.lang的4.import 与该类在同一个package的 -->
        <module name="UnusedImports" />

        <!-- 长度检查 -->
        <!-- 每行不超过150个字符 -->
        <module name="LineLength">
            <property name="max" value="300" />
        </module>
        <!-- 方法不超过150行 -->
        <module name="MethodLength">
            <property name="tokens" value="METHOD_DEF" />
            <property name="max" value="150" />
        </module>
        <!-- 方法的参数个数不超过5个。 并且不对构造方法进行检查-->
        <module name="ParameterNumber">
            <property name="max" value="10" />
            <property name="ignoreOverriddenMethods" value="true"/>
            <property name="tokens" value="METHOD_DEF" />
        </module>

        <!-- 空格检查-->
        <!-- 方法名后跟左圆括号"(" -->
        <module name="MethodParamPad" />
        <!-- 在类型转换时，不允许左圆括号右边有空格，也不允许与右圆括号左边有空格 -->
        <module name="TypecastParenPad" />
        <!-- Iterator -->
        <!-- <module name="EmptyForIteratorPad"/> -->
        <!-- 检查尖括号 -->
        <!-- <module name="GenericWhitespace"/> -->
        <!-- 检查在某个特定关键字之后应保留空格 -->
        <module name="NoWhitespaceAfter"/>
        <!-- 检查在某个特定关键字之前应保留空格 -->
        <module name="NoWhitespaceBefore"/>
        <!-- 操作符换行策略检查 -->
        <module name="OperatorWrap"/>
        <!-- 圆括号空白 -->
        <module name="ParenPad"/>
        <!-- 检查分隔符是否在空白之后 -->
        <module name="WhitespaceAfter"/>
        <!-- 检查分隔符周围是否有空白 -->
        <module name="WhitespaceAround"/>


        <!-- 修饰符检查 -->
        <!-- 检查修饰符的顺序是否遵照java语言规范，默认public、protected、private、abstract、static、final、transient、volatile、synchronized、native、strictfp -->
        <module name="ModifierOrder"/>
        <!-- 检查接口和annotation中是否有多余修饰符，如接口方法不必使用public -->
        <module name="RedundantModifier"/>


        <!-- 代码块检查 -->
        <!-- 检查是否有嵌套代码块 -->
        <module name="AvoidNestedBlocks"/>
        <!-- 检查是否有空代码块 -->
        <module name="EmptyBlock"/>
        <!-- 检查左大括号位置 -->
        <module name="LeftCurly"/>
        <!-- 检查代码块是否缺失{} -->
        <module name="NeedBraces"/>
        <!-- 检查右大括号位置 -->
        <module name="RightCurly"/>


        <!-- 代码检查 -->
        <!-- 检查是否在同一行初始化 -->
        <module name="AvoidInlineConditionals"/>
        <!-- 检查空的代码段 -->
        <module name="EmptyStatement"/>
        <!-- 检查在重写了equals方法后是否重写了hashCode方法 -->
        <module name="EqualsHashCode"/>
        <!-- 检查局部变量或参数是否隐藏了类中的变量 -->
        <module name="HiddenField">
            <property name="tokens" value="VARIABLE_DEF"/>
        </module>
        <!-- 检查是否使用工厂方法实例化 -->
        <module name="IllegalInstantiation"/>
        <!-- 检查子表达式中是否有赋值操作 -->
        <module name="InnerAssignment"/>
        <!-- 检查是否有"魔术"数字 -->
        <module name="MagicNumber">
            <property name="ignoreNumbers" value="0, 1"/>
            <property name="ignoreAnnotation" value="true"/>
        </module>
        <!-- 检查switch语句是否有default -->
        <module name="MissingSwitchDefault"/>
        <!-- 检查是否有过度复杂的布尔表达式 -->
        <module name="SimplifyBooleanExpression"/>
        <!-- 检查是否有过于复杂的布尔返回代码段 -->
        <module name="SimplifyBooleanReturn"/>

        <!-- 类设计检查 -->
        <!-- 检查类是否为扩展设计l -->
        <!-- <module name="DesignForExtension"/> -->
        <!-- 检查只有private构造函数的类是否声明为final -->
        <module name="FinalClass"/>
        <!-- 检查工具类是否有putblic的构造器 -->
        <module name="HideUtilityClassConstructor"/>
        <!-- 检查接口是否仅定义类型 -->
        <module name="InterfaceIsType"/>
        <!-- 检查类成员的可见度 -->
        <module name="VisibilityModifier"/>


        <!-- 其他检查 -->
        <!-- 文件中使用了System.out.print等
        <module name="GenericIllegalRegexp">
            <property name="format" value="System\.out\.print"/>
        </module>
        <module name="GenericIllegalRegexp">
            <property name="format" value="System\.exit"/>
        </module>
        <module name="GenericIllegalRegexp">
            <property name="format" value="printStackTrace"/>
        </module>-->

        <!-- 代码质量 -->
        <!-- 圈复杂度
        <module name="CyclomaticComplexity">
              <property name="max" value="2"/>
        </module> -->
    </module>
</module>
```

![1560507955802](D:\study\HealerJean.github.io\blogImages\1560507955802.png)



#### 2.1、扫描

![1560507981350](D:\study\HealerJean.github.io\blogImages\1560507981350.png)



#### 2.2、报错提示关闭  ->右下角 警察

![1560508050162](D:\study\HealerJean.github.io\blogImages\1560508050162.png)


















<br/>
<br/>

<font  color="red" size="5" >     
感兴趣的，欢迎添加博主微信
 </font>

<br/>



哈，博主很乐意和各路好友交流，如果满意，请打赏博主任意金额，感兴趣的在微信转账的时候，备注您的微信或者其他联系方式。添加博主微信哦。    

请下方留言吧。可与博主自由讨论哦

|微信 | 微信公众号|支付宝|
|:-------:|:-------:|:------:|
| ![微信](https://raw.githubusercontent.com/HealerJean/HealerJean.github.io/master/assets/img/tctip/weixin.jpg)|![微信公众号](https://raw.githubusercontent.com/HealerJean/HealerJean.github.io/master/assets/img/my/qrcode_for_gh_a23c07a2da9e_258.jpg)|![支付宝](https://raw.githubusercontent.com/HealerJean/HealerJean.github.io/master/assets/img/tctip/alpay.jpg) |



<!-- Gitalk 评论 start  -->

<link rel="stylesheet" href="https://unpkg.com/gitalk/dist/gitalk.css">
<script src="https://unpkg.com/gitalk@latest/dist/gitalk.min.js"></script> 
<div id="gitalk-container"></div>    
 <script type="text/javascript">
    var gitalk = new Gitalk({
		clientID: `1d164cd85549874d0e3a`,
		clientSecret: `527c3d223d1e6608953e835b547061037d140355`,
		repo: `HealerJean.github.io`,
		owner: 'HealerJean',
		admin: ['HealerJean'],
		id: 'AAAAAAAAAAAAAAA',
    });
    gitalk.render('gitalk-container');
</script> 

<!-- Gitalk end -->

