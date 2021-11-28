create schema cloud_user collate utf8mb4_general_ci;

create table tb_user
(
    id       bigint auto_increment comment '主键ID'
        primary key,
    username varchar(48)  not null comment '姓名',
    address  varchar(255) null comment '地址'
);

INSERT INTO cloud_user.tb_user (id, username, address) VALUES (1, '杨千', '湖南省衡阳市');
INSERT INTO cloud_user.tb_user (id, username, address) VALUES (2, '丁春秋', '陕西省西安市');
INSERT INTO cloud_user.tb_user (id, username, address) VALUES (3, '华程昱', '湖北省十堰市');
INSERT INTO cloud_user.tb_user (id, username, address) VALUES (4, '王二科', '四川省成都市');
INSERT INTO cloud_user.tb_user (id, username, address) VALUES (5, '胡汉山', '辽宁省沈阳市大东区');
INSERT INTO cloud_user.tb_user (id, username, address) VALUES (6, '西瓜', '山东省青岛市');