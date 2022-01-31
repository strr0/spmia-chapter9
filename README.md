# Zipkin
从spring boot 2.0开始，官方就不再支持使用自建Zipkin Server的方式进行服务链路追踪

### 两种方案

- 创建子模块zipkinsvr

添加maven依赖
```
        <dependency>
            <groupId>io.zipkin</groupId>
            <artifactId>zipkin-server</artifactId>
            <version>2.21.3</version>
        </dependency>
```
入口添加注解 ```@EnableZipkinServer``` 开启zipkin

- docker运行zipkin
```
docker pull openzipkin/zipkin
docker run -d -p 9411:9411 openzipkin/zipkin
```

### 报错及解决
- 报错配置
```
spring:
  zipkin:
    base-url: localhost:9411
```
- 报错原因  
空指针：Spring Cloud把localhost:9411当作了服务发现组件里面的服务名称
- 解决
```
spring:
  zipkin:
    base-url: http://localhost:9411
    discovery-client-enabled: false
```
