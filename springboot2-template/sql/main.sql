create table sys_user
(
    user_id     bigint auto_increment comment '用户ID'
        primary key,
    role_id     bigint       not null comment '角色ID',
    user_name   varchar(30)  null comment '用户账号',
    nick_name   varchar(30)  null comment '用户昵称',
    email       varchar(50)  null comment '用户邮箱',
    phonenumber varchar(11)  null comment '手机号码',
    sex         char         null comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) null comment '头像地址',
    password    varchar(100) null comment '密码',
    status      char         null comment '帐号状态（0正常 1停用）',
    del_flag    char         null comment '删除标志（0代表存在 2代表删除）',
    login_ip    varchar(128) null comment '最后登录IP',
    login_date  datetime     null comment '最后登录时间',
    create_by   varchar(64)  null comment '创建者',
    create_time datetime     null comment '创建时间',
    update_by   varchar(64)  null comment '更新者',
    update_time datetime     null comment '更新时间',
    remark      varchar(500) null comment '备注'
);

insert into sys_user (user_id, role_id, user_name, nick_name, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark) values (1, 1, 'admin', '若依', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-03-06 14:18:08', 'admin', '2023-12-17 17:16:16', '', '2024-03-06 14:18:07', '管理员');
insert into sys_user (user_id, role_id, user_name, nick_name, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark) values (2, 2, 'ry', '若依', 'ry@qq.com', '15666666666', '1', '', '$2a$10$aOCYB1saD7wXW4ih4GfEOel4u9JXex5GvZLKQlQcpBKQSFuLv4lz.', '0', '0', '127.0.0.1', '2024-03-02 14:56:27', 'admin', '2023-12-17 17:16:16', 'admin', '2024-03-02 14:56:26', '测试员');

create table sys_role_permission
(
    role_id       bigint not null comment '角色ID',
    premission_id bigint not null comment '权限ID',
    primary key (role_id, premission_id)
)comment '角色和权限关联表';

insert into sys_role_permission (role_id, premission_id) values (2, 1000);
insert into sys_role_permission (role_id, premission_id) values (2, 1007);

create table sys_role
(
    role_id     bigint auto_increment comment '角色ID'
        primary key,
    role_name   varchar(30)             not null comment '角色名称',
    role_key    varchar(100)            not null comment '角色权限字符串',
    role_sort   int(4)                  not null comment '显示顺序',
    status      char                    not null comment '角色状态（0正常 1停用）',
    del_flag    char        default '0' null comment '删除标志（0代表存在 2代表删除）',
    create_by   varchar(64) default ''  null comment '创建者',
    create_time datetime                null comment '创建时间',
    update_by   varchar(64) default ''  null comment '更新者',
    update_time datetime                null comment '更新时间',
    remark      varchar(500)            null comment '备注'
)comment '角色信息表';

insert into sys_role (role_id, role_name, role_key, role_sort, status, del_flag, create_by, create_time, update_by, update_time, remark) values (1, '超级管理员', 'admin', 1, '0', '0', 'admin', '2024-03-08 14:48:36', '', null, '超级管理员');
insert into sys_role (role_id, role_name, role_key, role_sort, status, del_flag, create_by, create_time, update_by, update_time, remark) values (2, '普通角色', 'common', 2, '0', '0', 'admin', '2024-03-08 14:48:36', '', null, '普通角色');

create table sys_permission
(
    premission_id   bigint auto_increment comment '权限ID'
        primary key,
    premission_name varchar(50)             not null comment '权限名称',
    perms           varchar(100)            null comment '权限标识',
    remark          varchar(500) default '' null comment '备注'
)comment '权限表';

insert into sys_permission (premission_id, premission_name, perms, remark) values (1000, '用户查询', 'system:user:query', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1001, '用户新增', 'system:user:add', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1002, '用户修改', 'system:user:edit', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1003, '用户删除', 'system:user:remove', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1004, '用户导出', 'system:user:export', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1005, '用户导入', 'system:user:import', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1006, '重置密码', 'system:user:resetPwd', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1007, '角色查询', 'system:role:query', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1008, '角色新增', 'system:role:add', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1009, '角色修改', 'system:role:edit', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1010, '角色删除', 'system:role:remove', '');
insert into sys_permission (premission_id, premission_name, perms, remark) values (1011, '角色导出', 'system:role:export', '');


create table sys_oper_log
(
    oper_id        bigint auto_increment comment '日志主键'
        primary key,
    title          varchar(50)   default '' null comment '模块标题',
    business_type  int(2)        default 0  null comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(100)  default '' null comment '方法名称',
    request_method varchar(10)   default '' null comment '请求方式',
    operator_type  int(1)        default 0  null comment '操作类别（0其它 1后台用户 2手机端用户）',
    oper_name      varchar(50)   default '' null comment '操作人员',
    dept_name      varchar(50)   default '' null comment '部门名称',
    oper_url       varchar(255)  default '' null comment '请求URL',
    oper_ip        varchar(128)  default '' null comment '主机地址',
    oper_location  varchar(255)  default '' null comment '操作地点',
    oper_param     varchar(2000) default '' null comment '请求参数',
    json_result    varchar(2000) default '' null comment '返回参数',
    status         int(1)        default 0  null comment '操作状态（0正常 1异常）',
    error_msg      varchar(2000) default '' null comment '错误消息',
    oper_time      datetime                 null comment '操作时间',
    cost_time      bigint        default 0  null comment '消耗时间'
)comment '操作日志记录';

create index idx_sys_oper_log_bt
    on sys_oper_log (business_type);

create index idx_sys_oper_log_ot
    on sys_oper_log (oper_time);

create index idx_sys_oper_log_s
    on sys_oper_log (status);

create table sys_logininfor
(
    info_id        bigint auto_increment comment '访问ID'
        primary key,
    user_name      varchar(50)  default ''  null comment '用户账号',
    ipaddr         varchar(128) default ''  null comment '登录IP地址',
    login_location varchar(255) default ''  null comment '登录地点',
    browser        varchar(50)  default ''  null comment '浏览器类型',
    os             varchar(50)  default ''  null comment '操作系统',
    status         char         default '0' null comment '登录状态（0成功 1失败）',
    msg            varchar(255) default ''  null comment '提示消息',
    login_time     datetime                 null comment '访问时间'
)
    comment '系统访问记录';

create index idx_sys_logininfor_lt
    on sys_logininfor (login_time);

create index idx_sys_logininfor_s
    on sys_logininfor (status);

create table sys_config
(
    config_id    int(5) auto_increment comment '参数主键'
        primary key,
    config_name  varchar(100) default ''  null comment '参数名称',
    config_key   varchar(100) default ''  null comment '参数键名',
    config_value varchar(500) default ''  null comment '参数键值',
    config_type  char         default 'N' null comment '系统内置（Y是 N否）',
    create_by    varchar(64)  default ''  null comment '创建者',
    create_time  datetime                 null comment '创建时间',
    update_by    varchar(64)  default ''  null comment '更新者',
    update_time  datetime                 null comment '更新时间',
    remark       varchar(500)             null comment '备注'
)comment '参数配置表';

insert into sys_config (config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark) values (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-03-09 18:24:01', '', null, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
insert into sys_config (config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark) values (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-03-09 18:24:01', '', null, '初始化密码 123456');
insert into sys_config (config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark) values (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2024-03-09 18:24:02', '', null, '深色主题theme-dark，浅色主题theme-light');
insert into sys_config (config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark) values (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'false', 'Y', 'admin', '2024-03-09 18:24:02', '', null, '是否开启验证码功能（true开启，false关闭）');
insert into sys_config (config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark) values (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2024-03-09 18:24:02', '', null, '是否开启注册用户功能（true开启，false关闭）');
insert into sys_config (config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark) values (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2024-03-09 18:24:02', '', null, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
