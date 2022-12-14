alter table book drop column author;

alter table book add column author_id int references author(id);