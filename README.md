# spring-cloud-examples
SpringCloud-实战  
架构：  
&nbsp;&nbsp;两个服务注册中心 --- 实现了注册中心的高可用  
&nbsp;&nbsp;两个配置中心     --- 分布式配置中心的高可用  
&nbsp;&nbsp;三个服务提供者并服务消费者  
&nbsp;&nbsp;两个服务网关 --- 实现了网关的高可用  
&nbsp;&nbsp;使用Eureka实现服务发现  
&nbsp;&nbsp;使用Config实现配置管理  Git 仓库存储 支持热更新  
&nbsp;&nbsp;使用Bus实现消息总线 RabbitMQ  
&nbsp;&nbsp;使用Hystrix实现断路器  
&nbsp;&nbsp;使用Zuul实现智能路由  
&nbsp;&nbsp;使用Feign实现微服务调用 支持软负载均衡  

服务端口分配：
&nbsp;&nbsp;服务注册中心1   -----   1100  
&nbsp;&nbsp;服务注册中心2   -----   1200  
&nbsp;&nbsp;服务提供者并消费者1 -----2100  
&nbsp;&nbsp;服务提供者并消费者2 -----2200  
&nbsp;&nbsp;服务提供者并消费者3 -----2300  
&nbsp;&nbsp;服务网关Zuul    -----   3100  
&nbsp;&nbsp;服务配置中心1   -----   4100  
&nbsp;&nbsp;服务配置中心2   -----   4200  
 
