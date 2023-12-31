create table member
(
    Member_ID varchar(20)  not null
        primary key,
    Full_Name varchar(500) not null,
    Position  varchar(50)  null,
    bod       date         null,
    Age       int          null,
    Email     varchar(100) null,
    Address   varchar(50)  null,
    Image     longblob     null
);

create table orders
(
    Book_id   varchar(30) not null
        primary key,
    Member_Id varchar(20) null,
    Date      date        null,
    Count     int         null
);

create table stock
(
    code        varchar(20)  not null
        primary key,
    Domain      varchar(20)  null,
    Domain_name varchar(100) null,
    Type        varchar(20)  null,
    Date        varchar(20)  null,
    Member      varchar(20)  null,
    Count       int          null
);


