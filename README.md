
数据库系统的课程项目（后端）
========================
（历时一天零**半个月**搞得差不多了）
# Introduction - 介绍： 机票查询与预定项目

***
ECNU-SEI  2023年春  DB  宫学庆教授
***

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

## 技术栈

- 编程语言：Java
- 构建工具：IDEA
- 后端框架：Springboot
- 插件支持：MybatisPlus
- 数据库：Mysql
- 数据库可视化工具：Navicat
- 接口测试：Postman


`~写表过程：entity层 service层(impl实现层)  （mapper层可不写）  controller层   测试`
Features - 特性
Requirements - 必要条件（环境，模块描述）  
Installation - 安装
Usage - 用法
ez   O(∩_∩)O
# Configuration - 配置（配置信息。）
- 部署：IDEA打开项目文件，添加spring框架支持，进入maven配置文件pom.xml 点击蓝色更新图标 会自动下载maven依赖
- 在application.yml配置文件里更改本地数据库连接 启动Application.java即可  
- 项目检查和运行前所需的sql建表命令 见flight.sql（大数据集）和flight_temp.sql(样例数据集)
- 项目检查需要的sql语句和结果见picture文件夹下的截图
- 上面只是一些样例截图，实际需要的url和功能在controller文件夹下每个java文件的注释中查看


# Development - 开发（关于开发的文档信息（API 等））
### Changelog - 更新日志（一个简短的历史记录（更改，替换或者其他。）  
尝试后端优化：
- [ ] *部署到服务器--没得报销2333*<br />
- [x] *购票的并发问题 可以优化  一开始只要显示有票航班 而且还要保证点进去也可能没票 okk*<br />
- [x] *选票订票功能okk+表增加列status表示订单状态：乘坐与否或过期*<br />
- [ ] *选座功能*<br />
- [ ] *头像文件等*<br />
- [ ] *评价系统和排序规则优化*<br />
- [x] *退改签功能okk*<br />
- [x] *我的订票历史查询okk*<br />
- [ ] *钱包和支付系统——使用机票票价——多乘客订票的改进*<br />
- [x] *sql执行时间显示与优化okkk*<br />
- [ ] *简单分页查询*
- [x] *低价机票推荐 和热门购票地点推荐*
- [x] *从订票记录看 既要时间又要航班信息  多表联查的返回值问题 要么嵌套list，要么新建一个整合元素的类，要么java.util.Map  要么注册复合entity类*
- [ ] Redis缓存token时效
- [ ] 邮箱注册验证模块
- [ ] 静态资源访问
- [ ] 嵌入式servlet容器
- [ ] 文件上传下载  
- [ ] 定时器
- [ ] 热部署的实现
- [ ] 持久层的优化
- [ ] security安全框架和数据库认证
修复了购票结束后票价归零的问题
修复了订票记录显示航班地点为数字的问题
修复了订票记录没有航班信息的问题
前端连带debug
为了保证数据量  生成了百万级别的随机数据集
Insert into Table2(field1,field2,...) select value1,value2,... from Table1 on duplicate key update Table1.id = Table2.id
可以保证不断合并过程中避免主键冲突
github推送被拒 因为单个文件有 ~120MB 的限制  不要上传过大的sql文件
IDEA的本地提交回滚策略：左下角git  选择本地某个记录 右键回滚  一定注意选择mixed选项  保留源码修改  只取消了本地提交
### FAQ - 常见问题（常见问题）  



### 云服务器部署过程：
- 阿里云+xshell+Xftp（可视化）+navicat（本地连接云端数据库） +宝塔面板自动部署软件

实例ID：i-bp1h0hhvaxtlel9fsv63
47.110.83.104  (公)
172.28.49.119  (私有)

porn.xml文件添加
<packaging>jar</packaging>
再双击meaven的package即可在target文件夹下得到hou-0.0.1-SNAPSHOT.jar包
坑点：一 maven配置里需要加上version ：mysql8.0以上   yml的配置要加上useSSL=false  数据库连接密码不能纯数字
二 mysql云端数据库报  java.sql.SQLException: null, message from server: "Host '*.*.*.*' is not allowed to connect  要在服务器命令行添加mysql的root权限
三 宝塔面板的配置： mysql要8.0   nginx要1.22  PHP要7.4   phpmyadmin要5.0  java一键部署要3.5
四  在一键部署里 设置/usr/java/jdk1.8.0_371/bin/java
47.110.83.104
 --server.port=8080   要加空格

五   阿里云安全组  入方向 要开各种端口  包括8080    宝塔也要对应开启入端口

FIN ！

    有问题问上帝lol
```(可以加编程语言名)
System.out.println("hello world");
```
```
来点抽象整活::
赛博佛祖
/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            佛祖保佑       永无BUG
*/

                      :;J7, :,                        ::;7:
                      ,ivYi, ,                       ;LLLFS:
                      :iv7Yi                       :7ri;j5PL
                     ,:ivYLvr                    ,ivrrirrY2X,
                     :;r@Wwz.7r:                :ivu@kexianli.
                    :iL7::,:::iiirii:ii;::::,,irvF7rvvLujL7ur
                   ri::,:,::i:iiiiiii:i:irrv177JX7rYXqZEkvv17
                ;i:, , ::::iirrririi:i:::iiir2XXvii;L8OGJr71i
              :,, ,,:   ,::ir@mingyi.irii:i:::j1jri7ZBOS7ivv,
                 ,::,    ::rv77iiiriii:iii:i::,rvLq@huhao.Li
             ,,      ,, ,:ir7ir::,:::i;ir:::i:i::rSGGYri712:
           :::  ,v7r:: ::rrv77:, ,, ,:i7rrii:::::, ir7ri7Lri
          ,     2OBBOi,iiir;r::        ,irriiii::,, ,iv7Luur:
        ,,     i78MBBi,:,:::,:,  :7FSL: ,iriii:::i::,,:rLqXv::
        :      iuMMP: :,:::,:ii;2GY7OBB0viiii:i:iii:i:::iJqL;::
       ,     ::::i   ,,,,, ::LuBBu BBBBBErii:i:i:i:i:i:i:r77ii
      ,       :       , ,,:::rruBZ1MBBqi, :,,,:::,::::::iiriri:
     ,               ,,,,::::i:  @arqiao.       ,:,, ,:::ii;i7:
    :,       rjujLYLi   ,,:::::,:::::::::,,   ,:i,:,,,,,::i:iii
    ::      BBBBBBBBB0,    ,,::: , ,:::::: ,      ,,,, ,,:::::::
    i,  ,  ,8BMMBBBBBBi     ,,:,,     ,,, , ,   , , , :,::ii::i::
    :      iZMOMOMBBM2::::::::::,,,,     ,,,,,,:,,,::::i:irr:i:::,
    i   ,,:;u0MBMOG1L:::i::::::  ,,,::,   ,,, ::::::i:i:iirii:i:i:
    :    ,iuUuuXUkFu7i:iii:i:::, :,:,: ::::::::i:i:::::iirr7iiri::
    :     :rk@Yizero.i:::::, ,:ii:::::::i:::::i::,::::iirrriiiri::,
     :      5BMBBBBBBSr:,::rv2kuii:::iii::,:i:,, , ,,:,:i@petermu.,
          , :r50EZ8MBBBBGOBBBZP7::::i::,:::::,: :,:,::i;rrririiii::
              :jujYY7LS0ujJL7r::,::i::,::::::::::::::iirirrrrrrr:ii:
           ,:  :@kevensun.:,:,,,::::i:i:::::,,::::::iir;ii;7v77;ii;i,
           ,,,     ,,:,::::::i:iiiii:i::::,, ::::iiiir@xingjief.r;7:i,
        , , ,,,:,,::::::::iiiiiiiiii:,:,:::::::::iiir;ri7vL77rrirri::
         :,, , ::::::::i:::i:::i:i::,,,,,:,::i:i:::iir;@Secbone.ii:::

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
  