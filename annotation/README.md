
## Spring 常见注解
#### Spring 1.x 常见注解

```java
@Transactional
@ManagedResource
```

#### Spring 2.x 常见注解
```java
@Component  // Spring 模式注解
```

#### Spring 3.x 常见注解
```java
@ComponentScan  // 装配注解
@Configuration  // Spring 模式注解
@Conditional
```

#### Spring 4.x 常见注解
```java
@Profile
```

#### Spring 5.x 常见注解
```java
@Indexed
```

## Spring 核心注解分类

### Spring 模式注解
```java
@Component
@Repository
@Service
@Controller
@Configuration
```

### 装配注解
```java
@Import
@ImportResource
@ComponentScan
```
### 依赖注入注解
```java
@Autowired
@Qualifier
```

## Spring 注解编程模型

### 编程模型

* 元注解，即标注在注解上的注解，比如 @Target
* Spring 模式注解，比如 @Component
* Spring 组合注解，比如 @SpringBootApplication，
* Spring 注解属性别名，注解属性覆盖


## Spring 注解属性别名

* 显式别名示例：@ComponentScan，子注解提供新的属性方法引用父（元）注解中的属性方法
* 隐匿别名示例：@SpringBootApplication
* 传递性别名

###
@SpringBootApplication，它组合了 @SpringBootConfiguration @EnableAutoConfiguration，
而 @EnableAutoConfiguration 注解中有 exclude 和 excludeName 两个属性
```java
public @interface SpringBootApplication {
	
	@AliasFor(annotation = EnableAutoConfiguration.class)
	Class<?>[] exclude() default {};

	
	@AliasFor(annotation = EnableAutoConfiguration.class)
	String[] excludeName() default {};
}
```

这里的意思就是说我显式的别名化我元标注（@EnableAutoConfiguration）上的属性，或者说 @SpringBootApplication 
注解如果想要继承 @EnableAutoConfiguration 注解中的属性的话，可以用 @AliasFor 来表示。


### 
```java
public @interface SpringBootApplication {
    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] scanBasePackages() default {};
}
```

@ComponentScan 注解可以理解为 @SpringBootApplication 的父注解，
在子注解  @SpringBootApplication 中提供了一个新的属性 scanBasePackages 来引用父注解（元注解） @ComponentScan
中的 basePackages 的属性，有继承又有映射的关系在里面。这种方式就是隐式别名。





