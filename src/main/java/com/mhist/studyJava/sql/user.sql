create database if not exists studyJava;

use studyJava;
create table IF NOT EXISTS user (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment '用户名',
                      password varchar(32) comment '密码',
                      nickname varchar(10) default '' comment '昵称',
                      email varchar(128) default '' comment '邮箱',
                      user_pic varchar(128) default '' comment '头像',
                      create_time datetime not null comment '创建时间',
                      update_time datetime not null comment '修改时间'
) comment '用户表';


-- 分类表
create table IF NOT EXISTS category (
                                    id int unsigned primary key auto_increment comment 'ID',
                                    category_name varchar(32) not null comment '分类名称',
                                    category_alias varchar(32) not null comment '分类标识',
                                    create_user int unsigned not null comment '昵称',
                                    create_time datetime not null comment '创建时间',
                                    update_time datetime not null comment '修改时间',
                                    constraint fk_category_user foreign key (create_user) references user(id) -- 外键
) comment '分类表';

-- 文章表
create table IF NOT EXISTS article (
                                        id int unsigned primary key auto_increment comment 'ID',
                                        title varchar(30) not null comment '文章标题',
                                        content varchar(1000) not null comment '文章内容',
                                        cover_pic varchar(128) comment "文章封面",
                                        state varchar(3) not null comment '文章状态，只能是发布或者是草稿',
                                        category_id int unsigned  comment '文章分类id',
                                        create_user int unsigned not null comment '创建人id',
                                        create_time datetime not null comment '创建时间',
                                        update_time datetime not null comment '修改时间',
                                        constraint fk_article_category foreign key (category_id) references category(id), -- 外键
                                        constraint fk_article_user foreign key (create_user) references user(id) -- 外键
) comment '文章表';