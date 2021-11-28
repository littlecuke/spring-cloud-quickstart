create schema cloud_order collate utf8mb4_general_ci;

create table tb_order
(
    id      bigint auto_increment comment '主键ID'
        primary key,
    user_id bigint       not null comment '用户ID',
    name    varchar(255) not null comment '订单名称',
    price   varchar(50)  not null comment '金额',
    num     int          not null comment '商品数量'
);

INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (101, 1, 'Apple 苹果iPhone13', '5999', 1);
INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (102, 2, '雅迪yadea新国标电动车', '5000', 1);
INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (103, 3, '小熊1.6L双人电饭锅', '159', 1);
INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (104, 4, '小米10双模5G骁龙888', '4399', 1);
INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (105, 5, 'OPPO Reno3 Pro', '4399', 1);
INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (106, 6, '美的新能效电风扇', '399', 1);
INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (107, 2, '西昊人体工程学椅', '1099', 1);
INSERT INTO cloud_order.tb_order (id, user_id, name, price, num) VALUES (108, 3, '梵班（FAMDBANN）', '31900', 1);