create table book_category
(
    category int not null references category(id),
    book int not null references book(id)
);