create table book_category
(
    category_id int references category(id),
    book_id int not null references book(id)
);