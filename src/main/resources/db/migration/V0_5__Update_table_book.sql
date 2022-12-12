alter table book drop column author;

alter table book add column author int references author(id);