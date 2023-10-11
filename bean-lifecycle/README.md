### Spring Bean 生命周期
1. Spring Bean 元信息配置阶段 
2. Spring Bean 元信息解析阶段 

3. Spring Bean 注册阶段 
4. Spring BeanDefinition 合并阶段 
5. Spring Bean Class 加载阶段 

6. Spring Bean 实例化前阶段 
7. Spring Bean 实例化阶段 
8. Spring Bean 实例化后阶段

9. Spring Bean 属性赋值前阶段 

10. Spring Bean Aware接口回调阶段

11. Spring Bean 初始化前阶段 
12. Spring Bean 初始化阶段 
13. Spring Bean 初始化后阶段 
14. Spring Bean 初始化完成阶段 

15. Spring Bean 销毁前阶段 
16. Spring Bean 销毁阶段 

17. Spring Bean 垃圾收集

### BeanFactory 是怎样处理 Bean 生命周期？
BeanFactory 的默认实现为 DefaultListableBeanFactory，其中 Bean生命周期与方法映射如下：
* BeanDefinition 注册阶段 - registerBeanDefinition
* BeanDefinition 合并阶段 - getMergedBeanDefinition
* Bean 实例化前阶段 - resolveBeforeInstantiation
* Bean 实例化阶段 - createBeanInstance
* Bean 实例化后阶段 - populateBean
* Bean 属性赋值前阶段 - populateBean
* Bean 属性赋值阶段 - populateBean
* Bean Aware 接口回调阶段 - initializeBean
* Bean 初始化前阶段 - initializeBean
* Bean 初始化阶段 - initializeBean
* Bean 初始化后阶段 - initializeBean
* Bean 初始化完成阶段 - preInstantiateSingletons
* Bean 销毁前阶段 - destroyBean
* Bean 销毁阶段 - destroyBean