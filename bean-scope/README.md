### Spring Bean 作用域之："request" Bean作用域

```bash
cd /Users/zhangquan/code/github.com/spring-framework-study

mvn clean package 

java -jar bean-scope/target/bean-scope-1.0-SNAPSHOT-war-exec.jar

## 加入调试端口，方便调试
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9527 bean-scope/target/bean-scope-1.0-SNAPSHOT-war-exec.jar
```