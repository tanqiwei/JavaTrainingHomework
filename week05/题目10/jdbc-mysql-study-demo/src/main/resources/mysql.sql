create table test.student
(
    name     varchar(20) null,
    serialNo varchar(20) not null,
    phone    varchar(20) null,
    address  varchar(20) null,
    constraint student_serialNo_uindex
        unique (serialNo)
);

alter table test.student
    add primary key (serialNo);