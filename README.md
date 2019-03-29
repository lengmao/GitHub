# hadoop
#### 说明：hadoop文件中目前只有一个简单的一个示例,主要是做了一个文件中同一个数据出现次数的统计

# spring-cloud-web
#### 说明：日常的一些练习文件
主要是spring boot+mybatis、定时任务、excle文件创建、表信息获取等练习

# 学习资料
#### 说明：日常积累的一些书籍
主要有java、javascript、数据库、云计算、理财等书籍收揽

# demo01
#### 说明：后端管理系统小项目

1、目录结构：

demo01  

     |--src  
          |--main  
               |--java
                      |--com.springboot.demo  
                                            |--busi 业务文件夹  
                                                   |--entity  
                                                   |--service  
                                                   |--mapper  
                                                   |--rest  
                                            |--common   
                                                   |--config 项目配置    
                                                   |--util 常用工具  
                                                   |--exception  
                                            |--sys 系统文件夹  
                                                   |--entity  
                                                   |--service  
                                                   |--mapper  
                                                   |--rest  
                                                   |--web 页面定向  
                |--resource 静态资源  
                          |--mapper xml映射文件 sql写于此处  
                          |--static js\css文件  
                          |--templates html文件  
                          |--allpication.yml  
          |--test  
          
2、准备

  （1）基本需要：mysql数据库、jdk1.8、maven  
  （2）下载路径：https://github.com/lengmao/GitHub，  
  （3）下载完成打开mysql数据库执行mydatabase.sql.  
  （4）将工程导入开发工具，运行demoApplication.java  
  
3、项目说明  

  （1）本项目采用spring boot + mybatis 完成项目的整体框架，用户登录认证使用shiro(权限管理没有用shiro);  
  （2）基础部分已经完成，如用户管理，角色管理，菜单管理，角色赋权，用户角色分配均已完成;  
  （3）如需开发其他业务功能，可以直接在busi中创建开发  
  （4）接口统一采用restful形式，以便于同一规范  
  （5）........
  
4、缺点  
    （1）由于前端html只是欠缺，页面整体的美观性不强  
    （2）用户交互体验差，（ps有时候自己都觉得不友好）  
    （3）....
    
5、这是自己独立完成的第一个demo,虽然没有特别独特的地方，但是也是对自己所学东西和所积累经验的一个验证，希望大佬们看到别嘲笑，同时，给小弟一点建议或者是直接给小弟开个小灶也可以啊
