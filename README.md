# 学生管理系统

基于 Spring Boot + MyBatis + jQuery 的前后端分离学生管理系统。

## 项目结构 
src/main/
├── java/com/example/
│ ├── controller/
│ │ ├── ClassController.java // 班级管理接口
│ │ ├── StudentController.java // 学生管理接口
│ │ └── UserController.java // 用户管理接口
│ ├── entity/
│ │ ├── Class.java // 班级实体类
│ │ ├── Student.java // 学生实体类
│ │ └── User.java // 用户实体类
│ ├── mapper/
│ │ ├── ClassMapper.java // 班级数据访问层
│ │ ├── StudentMapper.java // 学生数据访问层
│ │ └── UserMapper.java // 用户数据访问层
│ ├── service/
│ │ ├── ClassService.java // 班级业务逻辑层
│ │ ├── StudentService.java // 学生业务逻辑层
│ │ └── UserService.java // 用户业务逻辑层
│ └── StudentApplication.java // 应用程序入口
├── resources/
│ ├── static/
│ │ ├── css/
│ │ │ ├── common.css // 公共样式
│ │ │ ├── header.css // 头部样式
│ │ │ └── loading.css // 加载动画样式
│ │ ├── js/
│ │ │ ├── common.js // 公共函数
│ │ │ └── jquery.min.js // jQuery库
│ │ ├── class-edit.html // 班级编辑页面
│ │ ├── class-list.html // 班级列表页面
│ │ ├── common-nav.html // 公共导航栏
│ │ ├── error.html // 错误页面
│ │ ├── login.html // 登录页面
│ │ ├── student-detail.html // 学生详情页面
│ │ ├── student-edit.html // 学生编辑页面
│ │ └── student-list.html // 学生列表页面
│ ├── application.properties // 应用配置文件
│ ├── banner.txt // 启动Banner
│ └── db/
│ └── schema.sql // 数据库脚本

## 系统设计

### 1. 架构设计
- 采用前后端分离架构
- 后端提供 RESTful API
- 前端采用 jQuery + 原生 JS
- 数据库使用 MySQL

### 2. 数据库设计
1. 用户表(user)
   - id: 主键
   - username: 用户名(唯一索引)
   - password: 密码(MD5加密)
   - create_time: 创建时间
   - update_time: 更新时间

2. 班级表(class)
   - id: 主键
   - class_name: 班级名称
   - grade: 年级
   - create_time: 创建时间
   - update_time: 更新时间

3. 学生表(student)
   - id: 主键
   - student_no: 学号(唯一索引)
   - name: 姓名
   - age: 年龄
   - gender: 性别
   - phone: 联系电话
   - class_id: 班级ID(外键)
   - create_time: 创建时间
   - update_time: 更新时间

### 3. 业务逻辑

#### 用户管理模块
1. 登录流程
   - 前端采用表单提交用户名和密码
   - 后端进行MD5加密验证
   - 使用 localStorage 存储登录状态
   - 所有页面都需要验证登录状态

2. 权限控制
   - 未登录用户自动跳转到登录页
   - 已登录用户不能重复登录
   - 退出登录时清除本地存储

#### 学生管理模块
1. 学生列表
   - 支持按学号、姓名搜索
   - 支持按班级筛选
   - 显示学生基本信息
   - 提供编辑、删除、详情操作

2. 学生信息维护
   - 添加学生时学号不能重复
   - 编辑学生信息需要验证数据有效性
   - 删除学生需要确认
   - 详情页显示完整信息

#### 班级管理模块
1. 班级列表
   - 显示班级信息和学生数量
   - 支持按班级名称、年级搜索
   - 提供编辑和删除操作

2. 班级信息维护
   - 添加/编辑班级信息
   - 删除班级时检查是否有关联学生
   - 显示班级学生数量

### 4. 技术实现

#### 后端实现
1. Spring Boot
   - 使用 Spring MVC 处理请求
   - 统一异常处理
   - 跨域配置
   - 事务管理

2. MyBatis
   - 注解方式配置SQL
   - 动态SQL处理复杂查询
   - 一对一关联查询
   - 结果映射处理

3. 数据库
   - 使用MySQL存储数据
   - 建立合适的索引
   - 外键约束保证数据完整性

#### 前端实现
1. 页面布局
   - 响应式设计
   - 统一的导航栏
   - 模态框处理表单
   - 加载动画优化体验

2. 数据交互
   - Ajax 异步请求
   - RESTful API 调用
   - JSON 数据格式
   - Promise 处理异步

3. 用户体验
   - 表单验证
   - 加载状态提示
   - 操作确认提示
   - 错误信息展示

### 5. 关键技术点

1. 前后端分离
   - 接口设计规范
   - 数据格式统一
   - 跨域问题处理
   - 状态管理方案

2. 数据库设计
   - 三范式原则
   - 索引优化
   - 关联关系处理
   - 字段类型选择

3. 代码组织
   - MVC架构
   - 业务逻辑分层
   - 代码复用
   - 命名规范

4. 性能优化
   - 数据库查询优化
   - 前端资源加载
   - 缓存使用
   - 异步处理

### 6. 部署说明

1. 环境要求
   - JDK 1.8+
   - MySQL 5.7+
   - Maven 3.x
   - 现代浏览器

2. 部署步骤
   ```bash
   # 1. 克隆项目
   git clone [项目地址]

   # 2. 创建数据库
   mysql -u root -p
   create database student_db;
   use student_db;
   source schema.sql;

   # 3. 修改配置
   vim src/main/resources/application.properties
   # 修改数据库连接信息

   # 4. 编译打包
   mvn clean package

   # 5. 运行项目
   java -jar target/student-management-1.0-SNAPSHOT.jar
   ```

### 7. 开发建议

1. 代码规范
   - 遵循阿里巴巴Java开发手册
   - 使用统一的代码格式化工具
   - 添加适当的注释
   - 进行代码审查

2. 测试建议
   - 编写单元测试
   - 接口测试
   - 浏览器兼容性测试
   - 性能测试

3. 安全考虑
   - SQL注入防护
   - XSS防护
   - CSRF防护
   - 密码加密存储

4. 后续优化
   - 添加日志记录
   - 实现数据导出
   - 添加数据统计
   - 优化查询性能
   - 完善权限管理

## 常见问题

1. 跨域问题
   - 使用 @CrossOrigin 注解
   - 配置 CORS 过滤器
   - 代理服务器转发

2. 登录状态
   - 使用 localStorage 存储
   - 定期检查状态
   - 统一处理过期

3. 数据验证
   - 前端表单验证
   - 后端数据验证
   - 统一错误处理

4. 编码问题
   - 使用 UTF-8 编码
   - 配置数据库编码
   - 处理中文乱码

## 参考资料

1. Spring Boot 官方文档
2. MyBatis 官方文档
3. jQuery 官方文档
4. MySQL 官方文档
5. 阿里巴巴Java开发手册

## 版本历史

- v1.0.0 基础功能实现

## 贡献指南

1. Fork 项目
2. 创建功能分支
3. 提交变更
4. 发起 Pull Request

## 许可证

MIT License

## 联系方式

- 作者：[晚夜深秋]
- 邮箱：[2029249473@qq.com]
- GitHub：[https://github.com/li1023qwq]
