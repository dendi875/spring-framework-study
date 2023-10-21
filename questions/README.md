# @Bean 的处理流程是怎样的？

1. 解析范围：Configuration Class 中的 @Bean 方法
@Bean 方法它必须在 Configuration Class 中才能生效，并不是在你指定的任意的类中都会生效。
这里的原因是涉及到 Configuration Class 的处理，主要的类是 
ConfigurationClassParser，ConfigurationClassPostProcessor

2. 方法类型：静态 @Bean 方法和实例 @Bean 方法
无论是静态还是实例 @Bean 最终都会变成一个 BeanDefinition，然后注册到应用上下文，最终会创建出相应的 Bean，
它和 XML 创建 Bean 的方式本质没什么区别。