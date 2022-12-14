create sequence if not exists serial start with 7;

create table category
(
    id serial,
    name varchar unique not null ,
    primary key (id)
);