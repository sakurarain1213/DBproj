
数据库系统的课程项目  
========================
（后端 历时一天零**半个月**搞得差不多了）
# Introduction - 介绍
Summary -  航班查询购买项目
在cmd中     tree 项目硬盘地址 /f > 树状图输出地址result.txt   即可
DBproj  主要目录结构和介绍

```
│  pom.xml     在这里管理依赖仓库
│  README.md
│
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      ├─example
│  │  │      │  └─hou
│  │  │      │      │  HouApplication.java      启动类
│  │  │      │      │
│  │  │      │      │      
│  │  │      │      ├─controller                    前端交互第一层接口
│  │  │      │      │      AirportController.java
│  │  │      │      │      BookController.java
│  │  │      │      │      FlightController.java
│  │  │      │      │      UserController.java
│  │  │      │      │      UserInfoController.java
│  │  │      │      │      
│  │  │      │      ├─entity                          实体类 静态DB表类
│  │  │      │      │      Airport.java
│  │  │      │      │      Book.java
│  │  │      │      │      ExampleRecord.java
│  │  │      │      │      Flight.java
│  │  │      │      │      User.java
│  │  │      │      │      
│  │  │      │      ├─mapper                         形参mapper 可以不写
│  │  │      │      │      AirportMapper.java
│  │  │      │      │      BookMapper.java
│  │  │      │      │      FlightMapper.java
│  │  │      │      │      UserInfoMapper.java
│  │  │      │      │      UserMapper.java
│  │  │      │      │      
│  │  │      │      ├─result                         返回模板类
│  │  │      │      │      Result.java
│  │  │      │      │      ResultCode.java
│  │  │      │      │      ResultUtil.java
│  │  │      │      │      
│  │  │      │      └─service                         第二层接口类
│  │  │      │          │  AirportService.java
│  │  │      │          │  BookService.java
│  │  │      │          │  FlightService.java
│  │  │      │          │  RecordService.java
│  │  │      │          │  UserService.java
│  │  │      │          │  
│  │  │      │          └─impl                        末端具体实现类
│  │  │      │                  AirportServiceImpl.java
│  │  │      │                  BookServiceImpl.java
│  │  │      │                  CheckPassword.java
│  │  │      │                  FlightServiceImpl.java
│  │  │      │                  IdCardNumberUtils.java
│  │  │      │                  UserInfoServiceImpl.java
│  │  │      │                  UserServiceImpl.java
│  │  │              
│  │  └─resources
│  │      │  application.yml       统一配置文件
│  │      │  
│  │      ├─mapper
│  │      │      UserMapper.xml     自定义SQL文件
│  │      │      
│  │      ├─static
│  │      └─templates
│  └─test                            测试类
│      └─java
│          └─com
│              └─example
│                  └─hou
│                          HouApplicationTests.java
│
```

*springboot*+*mybatisplus*+*Mysql*
用**postman**测试接口  
`~写表过程：entity层 service层(impl实现层)  （mapper层可不写）  controller层   测试`
Features - 特性  
特有的优美
Requirements - 必要条件（环境，对所有项目，和所有子模块和库的描述。）  
Matlab  

# Configuration - 配置（配置信息。）
Installation - 安装
Usage - 用法   
ez   O(∩_∩)O   见maven配置文件pom.xml  自行下载依赖  
在yml配置文件里更改本地数据库连接 启动Application.java即可  

# Development - 开发（关于开发的文档信息（API 等））
### Changelog - 更新日志（一个简短的历史记录（更改，替换或者其他。）  
尝试后端优化：
*部署到服务器--没得报销2333*<br />
*购票的并发问题 可以优化  一开始只要显示有票航班 而且还要保证点进去也可能没票 okk*<br />
*选票订票功能okk+表增加列status表示订单状态：乘坐与否或过期*<br />
*选座功能*<br />
*头像文件等*<br />
*评价系统和排序规则优化*<br />
*退改签功能okk*<br />
*我的订票历史查询okk*<br />
*钱包和支付系统——使用机票票价——多乘客订票的改进*<br />
*sql执行时间显示与优化okkk*<br />
*简单分页查询*
Redis缓存token时效
邮箱注册验证模块
静态资源访问
嵌入式servlet容器
文件上传下载  
定时器
热部署的实现
持久层的优化
security安全框架和数据库认证
为了保证数据量  生成了百万级别的随机数据集
Insert into Table2(field1,field2,...) select value1,value2,... from Table1 on duplicate key update Table1.id = Table2.id
可以保证不断合并过程中避免主键冲突
github推送被拒 因为单个文件有 ~120MB 的限制  不要上传过大的sql文件
IDEA的本地提交回滚策略：左下角git  选择本地某个记录 右键回滚  一定注意选择mixed选项  保留源码修改  只取消了本地提交
### FAQ - 常见问题（常见问题）  
    有问题问上帝lol
```(可以加编程语言名)
System.out.println("hello world");
```
### Support - 支持  
### Dos - 文档（更多文档）  
### Contact - hsinchienwang@gmail.com  提交bug，功能要求，提交补丁，加入邮件列表，得到通知，加入用户或开发开发区群的介绍）  

***
分界线

# Authors and acknowledgment - 贡献者和感谢
### Wxj

# License - 版权信息（版权和许可信息（或阅读许可证）、法律声明）
当然是opensource辣

# 延伸阅读
nope
  