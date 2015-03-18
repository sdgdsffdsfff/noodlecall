# noodlecall 

noodlecall是一套分布式服务治理解决方案 <br><br>

DEMO: <br>

git clone https://github.com/fenglingguitar/noodlecommon.git <br>
mvn install -Dmaven.test.skip=true <br><br>

安装/启动本地MySQL，建立数据库：noodlecall_console_dev，用户：noodlecall，密码：noodlecall <br><br>

git clone https://github.com/fenglingguitar/noodlecall.git <br>
mvn package -P=dev -Dmaven.test.skip=true <br><br>

部署/运行 控制台 noodlecall-console-web/target/noodlecall.war <br><br>

访问http://localhost:8080/noodlecall/ <br><br>

运行 org.fl.noodlecall.demo.server.example.ServiceExporterDemoExampleNettyJson <br><br>

查看 控制台 -> 服务端，待状态上线后，运行 org.fl.noodlecall.demo.client.example.ServiceProxyFactoryDemoExample <br><br>

压力测试为：<br>
org.fl.noodlecall.demo.server.press.ServiceExporterDemoPressNettyJson <br>
org.fl.noodlecall.demo.client.press.ServiceProxyFactoryDemoPress <br><br>

此版本支持功能：<br>
1、服务自动注册、动态发现、动态配置 <br>
2、netty、jetty、servlet服务方式 <br>
3、json、Hessian序列化方式 <br>
4、随机负载、权重负载、性能负载 <br>
5、服务分组、灰度发布 <br>
6、服务拓扑图展示与操控 <br>
7、服务自动降级和恢复 <br>
8、性能指标的透明监控与图表展示 <br>
9、轻量性、易扩展、易夸平台 <br>
