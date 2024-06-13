create database if not exists studyJava;

use studyJava;
create table IF NOT EXISTS user (
                      id int unsigned primary key auto_increment comment 'ID',
                      name varchar(100) comment '姓名',
                      age tinyint unsigned comment '年龄',
                      gender tinyint unsigned comment '性别 1:男 2 女',
                      phone varchar(11) comment '手机号'
) comment '用户表';


insert into user (id, name, age, gender, phone) VALUES (null,'江某某',55,'1','18888888888');
insert into user (id, name, age, gender, phone) VALUES (null,'王某某',40,'1','18888888887');
insert into user (id, name, age, gender, phone) VALUES (null,'占某某',30,'1','18888888886');
insert into user (id, name, age, gender, phone) VALUES (null,'毛某某',26,'1','18888888885');
insert into user (id, name, age, gender, phone) VALUES (null,'程某某',27,'1','18888888884');
insert into user (id, name, age, gender, phone) VALUES (null,'曾某某',50,'1','18888888883');
insert into user (id, name, age, gender, phone) VALUES (null,'白某某',30,'1','18888888882');
insert into user (id, name, age, gender, phone) VALUES (null,'郑某某',32,'1','18888888881');