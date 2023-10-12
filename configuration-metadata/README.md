### Spring 内建 XML Schema 有哪些?

| 命名空间   | 所属模块       | Schema 资源 URL                                              |
| -------- | -------------- | ------------------------------------------------------------ |
| beans    | spring-beans   | https://www.springframework.org/schema/beans/spring-beans.xsd |
| context  | spring-context | https://www.springframework.org/schema/context/spring-context.xsd |
| aop      | spring-aop     | https://www.springframework.org/schema/aop/spring-aop.xsd    |
| tx       | spring-tx      | https://www.springframework.org/schema/tx/spring-tx.xsd      |
| util     | spring-beans   | https://www.springframework.org/schema/util/spring-util.xsd  |
| tool     | spring-beans   | https://www.springframework.org/schema/tool/spring-tool.xsd  |


### Spring配置元信息具体有哪些？
* Bean 配置元信息：通过媒介（如 XML、Proeprties 等），解析 BeanDefinition

* IoC 容器配置元信息：通过媒介（如 XML、Proeprties 等），控制 IoC 容器行为，比如注解驱动、AOP 等

* 外部化配置：通过资源抽象（如 Proeprties、YAML 等），控制 PropertySource

* Spring Profile：通过外部化配置，提供条件分支流程


### Extensible XML authoring 的缺点？
* 高复杂度：开发人员需要熟悉 XML Schema，spring.handlers，spring.schemas 以及 Spring API
* 嵌套元素支持较弱：通常需要使用方法递归或者其嵌套解析的方式处理嵌套（子）元素
* XML 处理性能较差：Spring XML 基于 DOM Level 3 API 实现，该 API 便于理解，然而性能较差
* XML 框架移植性差：很难适配高性能和便利性的 XML 框架，如 JAXB