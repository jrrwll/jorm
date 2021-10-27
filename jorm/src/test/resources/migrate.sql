create table role (
    id bigint not null auto_increment primary key, name varchar(63) not null
);

create table permission (
    id bigint not null auto_increment primary key, code varchar(63) not null
);

create table role_permission (
    id bigint not null auto_increment primary key, role_id bigint not null, permission_id bigint not null
);
