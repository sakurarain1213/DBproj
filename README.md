
数据库系统的课程项目  
========================
（后端 历时一天零**半个月**搞得差不多了）
# Introduction - 介绍
Summary - 概要     tree 项目硬盘地址 /f > 树状图输出地址result.txt   即可
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
│  └─test                         测试类
│      └─java
│          └─com
│              └─example
│                  └─hou
│                          HouApplicationTests.java
│
```

*springboot*+*mybatisplus*+*Mysql  *
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
  