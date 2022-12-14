create sequence if not exists serial start with 11;

create table author
(
    id     serial,
    name varchar unique not null ,
    particularity  varchar,
    birthdate date,
    primary key (id)
);