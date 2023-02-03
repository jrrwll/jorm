/*
sqlite3
.backup testdb.sqlite
*/

create table student (
    id bigint not null,
    name varchar(127) not null,
    birthday date
);

create table student_score (
    id bigint not null,
    student_id bigint not null,
    subject varchar(127) not null,
    score decimal(16, 6) not null default 0.0,
    time timestamp not null default current_timestamp
);