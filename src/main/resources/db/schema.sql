-- 学生表
CREATE TABLE student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_no VARCHAR(50) UNIQUE,
    name VARCHAR(50) NOT NULL,
    age INT,
    gender VARCHAR(10),
    phone VARCHAR(20),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    class_id BIGINT
);

-- 班级表
CREATE TABLE class (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(50) NOT NULL,
    grade VARCHAR(20) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 用户表
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 修改学生表，添加班级ID
ALTER TABLE student ADD FOREIGN KEY (class_id) REFERENCES class(id);

-- 插入班级测试数据
INSERT INTO class (class_name, grade) VALUES 
('一班', '2022级'),
('二班', '2022级'),
('三班', '2022级'),
('一班', '2023级'),
('二班', '2023级');

-- 插入管理员测试数据
INSERT INTO user (username, password) VALUES 
('admin', '21232f297a57a5a743894a0e4a801fc3'), -- 密码：admin
('teacher1', 'e10adc3949ba59abbe56e057f20f883e'), -- 密码：123456
('root', 'e10adc3949ba59abbe56e057f20f883e'); -- 密码：123456

-- 插入学生测试数据
INSERT INTO student (student_no, name, age, gender, phone, class_id) VALUES 
('2022001', '张三', 20, '男', '13800138001', 1),
('2022002', '李四', 19, '女', '13800138002', 1),
('2022003', '王五', 21, '男', '13800138003', 2),
('2022004', '赵六', 20, '女', '13800138004', 2),
('2022005', '孙七', 19, '男', '13800138005', 3),
('2023001', '周八', 18, '女', '13800138006', 4),
('2023002', '吴九', 19, '男', '13800138007', 4),
('2023003', '郑十', 18, '女', '13800138008', 5),
('2023004', '钱十一', 19, '男', '13800138009', 5),
('2023005', '陈十二', 18, '女', '13800138010', 5);
 