create table brand
(
    brand_id    varchar(20) not null,
    name        varchar(20) not null comment '品牌名字',
    description varchar(20) null comment '描述',
    constraint brand_brandId_uindex
        unique (brand_id)
)
    comment '品牌表';

alter table brand
    add primary key (brand_id);

create table categories
(
    category_id        varchar(20)             not null,
    category_name      varchar(20)             not null,
    parent_category_id varchar(20) default '0' null comment '虚拟货币数量',
    path               varchar(255)            not null comment '以id的路径/分隔',
    level              tinyint(1)              not null comment '1,2,3代表继承树第几层级',
    constraint Categories_category_id_uindex
        unique (category_id)
)
    comment '商品分类表';

alter table categories
    add primary key (category_id);

create table store
(
    seller_id   varchar(20)                        not null,
    name        varchar(20)                        not null,
    description varchar(20)                        null,
    address     varchar(20)                        null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null,
    constraint store_seller_Id_uindex
        unique (seller_id)
)
    comment '店家表';

alter table store
    add primary key (seller_id);

create table commodity
(
    commodity_id varchar(20)                          not null,
    category_id  varchar(20)                          not null,
    seller_id    varchar(20)                          not null,
    brand_id     varchar(20)                          not null,
    name         varchar(20)                          null,
    description  varchar(20)                          null,
    type         tinyint(1) default 1                 not null comment '1-正常商品
2-虚拟商品
3-活动赠送商品',
    price        int                                  not null comment '商品价格',
    is_show      tinyint(1)                           not null comment '1-展示，2-隐藏',
    create_time  datetime   default CURRENT_TIMESTAMP not null,
    update_time  datetime   default CURRENT_TIMESTAMP not null,
    constraint commodity_commodity_Id_uindex
        unique (commodity_id),
    constraint commodity_brand_brandId_fk
        foreign key (brand_id) references brand (brand_id),
    constraint commodity_categories_category_id_fk
        foreign key (category_id) references categories (category_id),
    constraint commodity_store_seller_Id_fk
        foreign key (seller_id) references store (seller_id)
)
    comment '商品表';

create index commodity_seller_Id_index
    on commodity (seller_id);

create table user
(
    user_id        varchar(20)                        not null comment '用户id',
    name           varchar(20)                        not null comment '用户名',
    nickname       varchar(20)                        null comment '昵称',
    gender         tinyint(1)                         null comment '性别',
    avatar_address varchar(255)                       null comment '头像链接下载地址/或存储在某些存储图片的服务器访问id',
    user_roles     int                                not null comment '用户角色类型.1-普通用户，2-会员用户，3-店家...',
    password       varchar(20)                        not null comment '用户密码',
    phone_no       varchar(20)                        null comment '手机号',
    email_address  varchar(20)                        null comment '邮箱',
    birthday       datetime                           null comment '生日',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    constraint user_user_id_uindex
        unique (user_id)
)
    comment '用户表';

create table order_info
(
    order_id         varchar(20)                          not null,
    user_id          varchar(20)                          not null,
    total_cost       int        default 0                 not null,
    order_status     tinyint(1) default 1                 not null comment '1-待付款，2-待收货，3-待评价',
    receiver_name    varchar(20)                          not null comment '接收人名字',
    receiver_address varchar(255)                         not null comment '接收人地址',
    createTime       datetime   default CURRENT_TIMESTAMP not null,
    updateTime       datetime   default CURRENT_TIMESTAMP not null,
    constraint order_order_id_uindex
        unique (order_id),
    constraint order_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '订单表';

create index order_user_id_index
    on order_info (user_id);

alter table order_info
    add primary key (order_id);

create table comment
(
    comment_id         varchar(20)                        not null,
    comment_user_id    varchar(20)                        not null,
    commodity_Id       varchar(20)                        not null,
    order_id           varchar(20)                        not null,
    comment_content    varchar(255)                       not null comment '评论',
    additional_content varchar(255)                       null comment '追加评论',
    create_time        datetime default CURRENT_TIMESTAMP not null,
    update_time        datetime default CURRENT_TIMESTAMP not null,
    constraint comment_comment_id_uindex
        unique (comment_id),
    constraint comment_commodity_category_id_fk
        foreign key (commodity_Id) references commodity (category_id),
    constraint comment_order_order_id_fk
        foreign key (order_id) references order_info (order_id),
    constraint comment_user_user_id_fk
        foreign key (comment_user_id) references user (user_id)
)
    comment '商品评价表';

alter table comment
    add primary key (comment_id);

create table item
(
    item_id      varchar(20) not null,
    order_id     varchar(20) not null,
    commodity_Id varchar(20) not null,
    current_cost double      not null,
    constraint item_item_id_uindex
        unique (item_id),
    constraint item_commodity_commodity_id_fk
        foreign key (commodity_Id) references commodity (commodity_id),
    constraint item_order_info_order_id_fk
        foreign key (order_id) references order_info (order_id)
)
    comment '订单条目';

create index item_order_id_index
    on item (order_id);

alter table item
    add primary key (item_id);

create index user_name_password_index
    on user (name, password);

create index user_user_roles_index
    on user (user_roles);

create table user_address
(
    user_address_id   varchar(20)                        not null comment '用户地址id',
    user_id           varchar(20)                        not null,
    isDefault         tinyint(1)                         not null comment '是否是默认地址',
    receiver_name     varchar(20)                        not null comment '接受人名字',
    receiver_address  varchar(20)                        null comment '接收人地址',
    receiver_phone_no varchar(20)                        not null comment '接收人电话',
    create_time       datetime default CURRENT_TIMESTAMP not null,
    update_time       datetime default CURRENT_TIMESTAMP not null,
    constraint user_address_user_address_id_uindex
        unique (user_address_id),
    constraint user_address_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '用户地址管理表';

create index user_address_user_id_index
    on user_address (user_id);

alter table user_address
    add primary key (user_address_id);

create table user_bind_info
(
    bind_id      varchar(20)                        not null comment '绑定id',
    user_id      varchar(20)                        not null,
    type         tinyint                            not null comment '绑定方式，微信，微博，QQ...',
    bind_type_id varchar(20)                        not null comment '调用第三方绑定接口返回的业务id',
    create_time  datetime default CURRENT_TIMESTAMP not null,
    update_time  datetime default CURRENT_TIMESTAMP null,
    constraint user_bind_info_bind_id_uindex
        unique (bind_id),
    constraint user_bind_info_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '用户第三方绑定表';

alter table user_bind_info
    add primary key (bind_id);

create table user_pay_for_record
(
    record_id   varchar(20)                        not null,
    user_id     varchar(20)                        not null,
    order_id    varchar(20)                        null,
    payfor_type tinyint  default 1                 not null comment '1-微信支付
2-支付宝支付
3-网银支付
',
    parfor_cost int      default 0                 not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null,
    constraint user_pay_for_record_record_id_uindex
        unique (record_id),
    constraint user_pay_for_record_order_info_order_id_fk
        foreign key (order_id) references order_info (order_id),
    constraint user_pay_for_record_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '用户交易记录';

alter table user_pay_for_record
    add primary key (record_id);

