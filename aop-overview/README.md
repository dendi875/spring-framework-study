# AOP 概念

Aspect 相当于一个 Class 一样，Join point 相当于一个方法，Advice 相当于方法里的具体步骤。

一个 Aspect 可能会对应多个 Join point，一个 Join point 会对应多个 Advice。1:N:N 关系。

pointCut 是一个筛选操作，筛选出符合条件的 JoinPoint，筛选出来之后由 Advice去执行，Advice 是一个执行操作。

## AOP 定义

面向切面编程的缩写

### AspecJ

Aspect-oriented programming is a way of modularizing crosscutting concerns much like object-oriented programming is a way of modularizing common concerns.

### Spring

Aspect-oriented Programming (AOP) complements Object-oriented Programming (OOP) by providing another way of thinking about program structure. The key unit of modularity in OOP is the class, whereas in AOP the unit of modularity is the aspect. Aspects enable the modularization of concerns (such as transactio management) that cut across multiple types and objects.

## Aspect （切面）定义

### AspectJ

aspect are the unit of modularity for crosscutting concerns. They behave somewhat like Java classes, but may also include pointcuts, advice and inter-type declarations.

在 AspectJ 里切面是它的横切关注点的单元模块。

### Spring

A modularization of a concern that cuts across multiple classes.

在 Spring 里切面是多个类之间关注的模块，可以认为它是一个组织形式，这个组织形式里包含了它横切的一些类或方法。

## Join point （连接点）定义

也叫加入点或横切点

### AspectJ

A join point is a well-defined point in the program flow. A pointcut picks out certain join points and values at those points.

### Spring

A point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution.

在 Spring 中连接点是一段执行程序，只不过这段执行程序要去处理方法执行时候或异常处理时候的一人拦截动作。

Spring 里 Joint Point 包含一个方法或一个异常的处理，在 AspectJ 里它可以包含多个 Joint Point，你可以把它组织成一个形式。

## PointCut （切入点/判断点）定义

挑选出连接点，判断哪些 join point 需要横切。

### Aspectj

pointcuts pick out certain join points in the program flow.

从程序流中挑选中某个 join point 的一个动作就是切入点。

### Spring

A predicate that matches join points.

它去匹配 joint point 的一个条件。

## Advice（通知或者动作） 定义

advice 它本来就是一段代码或者一段程序，它是在一个我们目标代码执行前后的一个动作。

Aspect 做为一个模块来组织 Join point，由当把 Joint point 挑选出来之后，下一步就是要对它的行为来做一个执行。

### AspectJ

So pointcuts pick out join points. But they don't do anything apart from picking out join points. To actually implement crosscutting behavior, we use advice. Advice brings together a pointcut (to pick out join points) and a body of code (to run at each of those join points).

### Spring

Action taken by an aspect at a particular join point. Different types of advice include “around”, “before” and “after” advice. Many AOP frameworks, including Spring, model an advice as an interceptor and maintain a chain of interceptors around the join point.

Advice 包含了几种方式:

around:  Spring帮我们围绕着，但它不做动作，这个动作做不做需要我们自己判断，Spring把它拦截住，至于执不执行，是需我们自己控制的。

before/after: 它是被动触发的，目标方法执行前或后去做。

可以认为 around 是一个拦截动作，而 before/after 是拦截前后的一个执行动作。即拦截动作是由框架内部帮我们做了，但动作的执行有被动和主动执行的区别。



## Introduction 定义

### AspectJ

Inter-type declarations in AspectJ are declarations that cut across classes and their hierarchies. They may declare members that cut across multiple classes, or change the inheritance relationship between classes.

可以在运行时或者编译时动态的修改当前类的结构，使它可以继承某个接口或类。

### Spring 

Declaring additional methods or fields on behalf of a type. Spring AOP lets you introduce new interfaces (and a corresponding implementation) to any advised object.

它是一种辅助的方法，它允许程序有多个不同的接口实现。可以动态的追加实现的接口或实现的类。


## Spring AOP 代理实现
* JDK 动态代理实现--基于接口代理
```java
org.springframework.aop.framework.JdkDynamicAopProxy
```
* CCLIB 动态代理实现--基于类代理（字节码提升）
```java
org.springframework.aop.framework.CglibAopProxy
```
可以看到 JdkDynamicAopProxy 与 CglibAopProxy 这两个类都不是 public class,
所以需要某个方式来进行封装，当我的目标对象是接口时就用 JdkDynamicAopProxy，是类的就用 CglibAopProxy（字节码的提升）

* AspectJ 适配实现
```java
org.springframework.aop.aspectj.annotation.AspectJProxyFactory
```

# Spring AOP 与 AspectJ AOP 的区别

* AspectJ 是 AOP 的完整实现，Spring AOP 则是部分实现
* Spring AOP 比 AspectJ 使用更简单
* Spring AOP 整合了 AspectJ 注解与 Spring IoC 容器
* Spring AOP 仅支持基于代理模式的 AOP
* Spring AOP 仅支持方法级别的 Pointcuts

# 对应关系

一个 Aspect 可以对应多个 JoinPoint
一个 JoinPoint 可以对应多个 Advise
一个 Advice 对应一个 Advisor
一个 Advice 可以对应多种拦截的实现动作（通过 AdvisorAdapter、DefaultAdvisorAdapterRegistry 来进行扩展 Advice ）

# 参考资料

* https://docs.spring.io/spring-framework/docs/5.2.25.RELEASE/spring-framework-reference/core.html#aop-pointcuts
* https://eclipse.dev/aspectj/doc/released/progguide/index.html
* https://eclipse.dev/aspectj/doc/released/adk15notebook/index.html