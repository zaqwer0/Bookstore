INSERT INTO books (id, title, author_id, price, published_year, bookstore_id)
VALUES ('333e8400-e29b-41d4-a716-006655440000', 'Book One', '311e8400-e29b-41d4-a716-446655440000', 19.99, 2020,
        '111e8400-e29b-41d4-a716-446655440000'),
       ('333e8400-e29b-41d4-a716-446655440001', 'Book Two', '311e8400-e29b-41d4-a716-446655440001', 29.99, 2021,
        '111e8400-e29b-41d4-a716-446655440001'),
       ('333e8400-e29b-41d4-a716-446655440002', 'Book Three', '311e8400-e29b-41d4-a716-446655440002', 25.99, 2019,
        '111e8400-e29b-41d4-a716-446655440002'),
       ('333e8400-e29b-41d4-a716-446655440003', 'Book Four', '311e8400-e29b-41d4-a716-446655440003', 15.99, 2018,
        '111e8400-e29b-41d4-a716-446655440003'),
       ('333e8400-e29b-41d4-a716-446655440004', 'Book Five', '311e8400-e29b-41d4-a716-446655440004', 12.99, 2022,
        '111e8400-e29b-41d4-a716-446655440004');


 List<Book> books = bookRepository.findAll();

        BookSortedStrategy bookSortedStrategy = Optional.ofNullable(bookSortedStrategies.get(sortType))
                .orElse((unsorted) -> unsorted.stream()
                        .sorted(Comparator.comparing(Book::getTitle))
                        .toList()
                );

        books = bookSortedStrategy.sortBooks(books);

        return bookMapper.toBooksResponse(books);