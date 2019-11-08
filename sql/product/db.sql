drop table if exists category;

/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category
(
   id                   int not null comment '主键',
   category_name        varchar(100) comment '类别的名称',
   category_code        varchar(50) comment '类别编码',
   parent_id            int comment '父级id，顶级默认为0',
   hierarchy            int comment '层级，从1开始，如果父类层级为1，子类就为2',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   create_no            varchar(100) comment '创建人号',
   update_no            varchar(100) comment '更新人',
   primary key (id)
);

alter table category comment '商品分类表';


drop table if exists products;

/*==============================================================*/
/* Table: products                                              */
/*==============================================================*/
create table products
(
   id                   char(64) comment '主键id',
   name                 varchar(50) comment '商品名称',
   description          varchar(2000) comment '商品描述',
   price                decimal comment '商品价格原始价格',
   promotion_code       varchar(100) comment '商品促销类别（有很多促销类型）',
   down_payment         decimal comment '定金',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   create_no            varchar(100) comment '创建人号',
   update_no            varchar(100) comment '更新人'
);

alter table products comment '商品表';

drop table if exists products_img;

/*==============================================================*/
/* Table: products_img                                          */
/*==============================================================*/
create table products_img
(
   img_path             varchar(50) comment '图片地址',
   description          varchar(2000) comment '商品描述',
   sort                 int comment '图片排序',
   rel_id               varchar(64) comment '关联的id，如商品id等',
   type                 tinyint comment '图片在哪里展示(1-首页，2-预览页，3-颜色选择)',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   create_no            varchar(100) comment '创建人号',
   update_no            varchar(100) comment '更新人',
   primary key ()
);

alter table products_img comment '商品图片表';