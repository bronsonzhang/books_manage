##                                 Java图书管理系统

> 基于jsp+servlet+JavaBean  (临时服务器访问异常，但是代码是没error的)
#### 基本信息：

- **开发环境**： windows+jdk1.8+Tomcat9+IDEA+mysql
注意：我用的IDE是`jetbrains IntelliJ IDEA`，不知道导入Eclipse会出现什么问题。
- **注意事项**：调试之前请创建名为books_manage的数据库，相关sql脚本为项目根目录下`books_manage_mysql.sql`/`books_manage_sqlserver.sql`, 账户密码都是admin
- **存在问题**: 会因为Tomcat的版本而出现部分异常
- **关于图片**: 本项目涉及图片上传与富文本编辑器，但是，每当我们开始Run或者Debug项目，`tomcat/webapps/ROOT`这个项目会被清空然后重新写入编译好的Java代码。由于图片保存在本项目`src/main/webapp/assets/bookImg`下，所以每次运行，项目图片会清空。当然也可以将图片写入项目目录外部，但是前台展示图片用绝对路径的话就相当麻烦了，此处我不纠结了。只要tomcat一直开着，图片上传是完全没问题的。


