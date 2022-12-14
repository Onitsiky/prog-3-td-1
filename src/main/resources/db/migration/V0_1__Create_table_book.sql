create sequence if not exists serial start with 11;

create table book
(
    id     serial,
    author varchar,
    title  varchar,
    page_number int not null default 0,
    release_date date,
    primary key (id)
);
