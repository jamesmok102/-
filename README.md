# 网络应用开发——电子商务网站

莫嘉偉 201935343037

## 基本功能要求
### 顾客
* 用户的注册，登录，注销
* 用户购买流程（浏览/查询 -> 添加至购物篮 -> 付款
### 销售
* 商品目录的管理（包括最基本的添加，删除，修改等操作）
* 后台销售统计报表，销售状态
* 客户的 **浏览/购买** 日志记录

## 设计思路
由于这次的实验内容较多，所以我简单地把JAVA的对象进行分类，写代码的时候更容易整理思路，即使发现问题也容易比较解决

### 后端的项目结构
* VO：储存数据库表的字段
* DAO：访问数据库，结合VO使用
* EXCEPTION：定义异常
* FACTORY：工厂，生成对象用
* FILTER：过滤器，自动登入或限制页面访问
* SERVICE：业务层，调用DAO中的方法
* SERVLET：JAVA WEB的核心
* DBC：访问数据库的公共类
* UTIL：其他公共类，如MD5转换，重写COOKIE等

### 前端的项目结构
* CSS：CSS文件，存一些前端和后端模板所用到的CSS文件
* IMAGES：存后端模板用到的图片
* JS：存JSP文件所用到的JS文件，大部份都是用来做HTML的INPUT验证
* PAGES：本次实验的核心，分为Front和Back，存放在Front中的JSP文件是用户能直接访问的，而存放在Back中的JSP文件是管理员访问的
* UPLOAD：存放商品图片，通常从管理员后台上传

### 关于模板
前台和后台也有用模板，不用模板的话很不美观，关于前台的logo，我没有改，打算留到课程设计时，我重新自已设计一个新logo。关于后台的，就是一个后台模板，自已不会写模板，用了开源模板。

### 关于Front和Back
这个Front和Back经常出现在FACTROY，SERVICE，SERVLET和PAGES中，因为前端和后端也会用到相同的东西，例如面向用户会显示商品列表，在后台也会显示商品列表，但后台可以增加删除修改商品，但前端只可以查看。对于这种相同又不相同的东西分为前端和后端，命名为Front和Back

### Web Server处理流程
首先，有Request进入Web Server，直接访问Servlet(或者经JSP再到Servlet)，直接用Factory生成Service对象，然后访问Service对象，在Service层中再用Factory生成DAO对象，访问DAO对象后调用DAO方法，通常传入一个VO类，`List<VO>`(多个VO类)，`Map<String, Object>`(返回多个不同类型的对象)，在DAO中调用DBC对数据库进行访问，将数据库返回的结果返回到VO中，然后经过一层层的返回后回到Servlet，最后根据结果判断要forward到哪个JSP面页上

### 数据库设计
Tables分别有Admin(管理员)、Member(用户)、Genre(商品分类)、Commodity(商品)、Orders(订单)、Details(订单详情)

#### Genre(商品分类)
* gid：商品分类ID(自动生成)
* title:商品分类名称

#### Admin(管理员)
* aid：管理员ID
* password：管理员密码
* lastDate：最后登入的日期

#### Member(用户)
* mid：用户ID
* password：用户密码
* name：用户名称
* phone：用户电话号码
* address：用户地址
* regDate：注册日期

#### Commodity(商品)
* cid：商品ID(自动生成)
* gid：商品种类ID
* aid：管理员ID
* title：商品名称
* pubDate：增加日期
* price：商品价格
* amount：商品数量
* note：商品描述
* photo：商品相片(存放路径)
* status：商品状态| 0:下架 | 1:上架 | 2:删除

#### Orders(订单)
* oid：订单ID(自动生成)
* mid：用户ID
* name：用户名称
* phone：用户电话号码
* address：用户地址
* creDate：订单创建日期
* totalPay：价格

#### Details(订单详情)
* odid：订单详情ID(自动生成)
* oid：订单ID
* cid：商品ID
* title：商品名称
* price：商品价格
* amount：商品数量

#### ShopCar(购物车)
* cid：商品ID
* mid：用户ID
* amount：商品数量

## 环境配置

### 本地配置
IDE：IntelliJ IDEA 2021.2.3
MySQL操作介面：Navicat Premium 15
Local Tomcat：apache-tomcat-9.0.54
Local MySQL：MySQL 8.0.27

### 远端配置
Server：腾讯云 轻量应用服务器 2核4G CentOS7.6
Remote配置Server介面：XShell 7
Remote文件上传介面：XFtp 7
Remote Tomcat：apache-tomcat-9.0.56
Remote MySQL：MySQL 5.7.36

### 本地Tomcat配置过程

首先，在Idea的Run/Debug Configuration添加Tomcat Server，配置如下：
![](https://i.imgur.com/uqXKeEU.png)

然后在本地运行
![](https://i.imgur.com/q036rPc.png)

### 本地MySQL的配置过程

在我主机上的MySQL是很久以前就配置好的，设定为开机启动，当时没做笔记，现在忘了，但是我可以说一下我如何连接和使用MySQL

打开Navicat Premium 15然后建立连接
![](https://i.imgur.com/Z8VtIxu.png)

然后就可以在命令行界面输入命令配置数据库
![](https://i.imgur.com/tIJgCjP.png)

### 远端MySQL配置

在Xshell7中配置Server连接
![](https://i.imgur.com/GORG6qe.png)

通过wget下载并安装MySQL官方的Yum Repository
`wget -i -c http://dev.mysql.com/get/mysql57-community-release-el7-10.noarch.rpm`

使用 yum 安装
`yum -y install mysql57-community-release-el7-10.noarch.rpm`

安装MySQL服务器
`yum -y install mysql-community-server`

启动MySQL
`service mysqld start`

检查MySQL运行状态
`service mysqld status`

正常结果如图所示:
![](https://i.imgur.com/wqpjRi9.png)

通过日志文件找到此时root用户的密码，以便登录
grep "password" /var/log/mysqld.log
![](https://i.imgur.com/Fp4vSbn.png)

使用root用户登录数据库，密码是上一步的
`mysql -uroot -p`

先选择数据库mysql，在修改数据库密码
```sql=
# 选择数据库
use mysql;
# 重置密码
update mysql.user set authentication_string=PASSWORD('newpassword') where User='root'; 
# 刷新权限
flush privileges;
# 退出登录
quit
```

重启mysql服务
`service mysqld restart`

数据库root用户登录
`mysql -uroot -p`

登录数据库之后，此时什么操作都执行不了，因为我们此次登录才算是第一次登录数据库，
而MySQL首次登陆默认必须修改密码后才能操作数据库
```sql=
#修改密码
ALTER USER 'root'@'localhost' IDENTIFIED BY 'your password';
# 刷新权限
flush privileges;
# 退出登录
quit
```

创建远程连接用户并授权
```sql=
grant all privileges on *.* to 'jamesmok'@'%' identified by 'password' with grant option;
```

打开Navicat Premium 15然后建立连接
![](https://i.imgur.com/mLo51y4.png)

### 远程Tomcat配置
安装jdk1.8，如要安装其他版本，可以通过yum -y list java* 查询可安装的版本
`yum -y install java-1.8.0-openjdk`

检测是否安装成功
`java -version`
![](https://i.imgur.com/RN6Imf1.png)

建立tomcat路径
`mkdir /usr/local/tomcat`

上传tomcat档案，利用XFtp7上传
![](https://i.imgur.com/3dBCaVc.png)

解压档案
`tar -zxvf apache-tomcat-9.0.56.tar.gz`

进入tomcat
`cd apache-tomcat-9.0.56`

进入bin
`cd bin`

这时可以看到有以下档案，其中`startup.sh`为开启tomcat，`shutdown.sh`为关闭tomcat
![](https://i.imgur.com/1hNohg9.png)

### 配置WebApp项目
在本地IDE中经过编译的项目在out中，我们只要把它放到Server的WebApp中就成功配置
![](https://i.imgur.com/MsXPIUA.png)
![](https://i.imgur.com/KF0malM.png)

## 功能测试

首先从后台开始测试，然后回到前台

### 后台登入测试
网址和帐密在实验报告里
![](https://i.imgur.com/nFGRFJd.png)
登入成功后有提示
![](https://i.imgur.com/bYImQZW.png)
登入后画面
![](https://i.imgur.com/1tbgtn8.png)



### 后台测试
分为三个部份，分别为用户管理，商品管理，和订单管理

#### 用户管理
用户管理画面
![](https://i.imgur.com/NW9ZNLN.png)


#### 商品管理
分别有：增加分类、分类列表、增加商品、商品列表、上架商品、下架商品、删除商品

#### 增加分类
增加一个新分类，例如叫做**饮料**
![](https://i.imgur.com/jz0TvfS.png)
增加成功有提示
![](https://i.imgur.com/zF6xy3p.png)

#### 分类列表
查看分类列表，可以看到刚增加的分类成功显示
![](https://i.imgur.com/nWyHf1F.png)
可以对分类进行修改，例如把**饮料**改成**食品或饮料**
![](https://i.imgur.com/h2KP5oU.png)
修改成功后如图所示
![](https://i.imgur.com/BwRFXib.png)

#### 增加商品
这个是增加商品介面
![](https://i.imgur.com/aiNJukn.png)
成功后有提示
![](https://i.imgur.com/mEBUrPM.png)

#### 商品列表
商品列表包括：上架商品、下架商品和删除商品，一共显示出来。若要分别查看，也设有其他列表
![](https://i.imgur.com/nMK6BV0.png)
测试下架功能，例如下架**可口可乐杯**，下架后
![](https://i.imgur.com/yjyLmWG.png)
测试上架功能，例如上架**维逹**，上架后
![](https://i.imgur.com/R1RJOuO.png)
测试删除功能，例如删除**CCNA**，删除后
![](https://i.imgur.com/02PL6Ck.png)
测试修改操作，例如修改可口可乐杯，把它的价钱改成100元
![](https://i.imgur.com/snGi0MH.png)


#### 上架商品
显示上架商品
![](https://i.imgur.com/qp7mBRK.png)

#### 下架商品
显示下架商品
![](https://i.imgur.com/IXQDoRW.png)

#### 删除商品
显示删除商品
![](https://i.imgur.com/LH0L6oD.png)
测试彻底删除功能，删了就没了
![](https://i.imgur.com/w6asSKr.png)

### 订单功能
现在没有订单，等下去前台买商品时再做测试

### 前台测试
前台页面，没错，我没有放任何东西去首页，上面这个logo我下次课程设计时再改
![](https://i.imgur.com/EqTk5Ud.png)

#### 前台注册
我们没有帐号，先去注册一个
![](https://i.imgur.com/hBwvsYH.png)


#### 前台登入
输入帐号密码后登入
![](https://i.imgur.com/4PhbFGg.png)
登入后，会发现前台的nav列发生改变，没有了注册和登录。相对的多了个人信息、全部订单和登出
![](https://i.imgur.com/j1Mh0dh.png)

#### 个人信息
我设定了个人信息不整全的话无法付款的，如图所示
![](https://i.imgur.com/AoG9q2D.png)
所以我们要去完善个人信息
![](https://i.imgur.com/9luJ7pO.png)
填完信息后
![](https://i.imgur.com/UxdSaeX.png)

#### 商城列表
首先来到商城列表
![](https://i.imgur.com/Yr0h1bK.png)
可以查看商品详情
![](https://i.imgur.com/ZPB1CTz.png)
想买的话可以先加入购物车
![](https://i.imgur.com/VKffIbY.png)

#### 购物车
这是购物车页面，为什么有两本，因为刚才为了测试没有完善个人信息时增加购物车并付款的情况，显然井没有付款成功。
![](https://i.imgur.com/E56e62X.png)
买了几本不同的书，为了方便测试
![](https://i.imgur.com/tHOhEG9.png)
测试删除功能，删除**java web**
![](https://i.imgur.com/4PzbR1Q.png)
买多两本算法竞赛，井更新购物车
![](https://i.imgur.com/JG3sv1S.png)
现在提交订单，提交完购物车清空
![](https://i.imgur.com/cRrn6tH.png)

#### 全部订单
看了刚才提交的订单
![](https://i.imgur.com/rAEWSTI.png)
可以按一下订单编号，查看订单详情
![](https://i.imgur.com/O7CCPdd.png)

#### 查看订单(后台)

刚才是没有提交订单，所以后台才没有显示订单，现在回到后台
![](https://i.imgur.com/sxU1FnI.png)
可以按一下订单编号，查看订单详情
![](https://i.imgur.com/WvdKdBo.png)



        




