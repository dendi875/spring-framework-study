
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
* Spring 组合注解
* Spring 注解属性，属性别名和属性覆盖