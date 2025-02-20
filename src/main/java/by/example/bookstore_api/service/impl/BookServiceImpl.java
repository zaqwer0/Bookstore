package by.example.bookstore_api.service.impl;

import by.example.bookstore_api.exception.BookstoreException;
import by.example.bookstore_api.mapper.BookMapper;
import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.repository.BookRepository;
import by.example.bookstore_api.service.BookService;
import by.example.bookstore_api.strategy.sortAuthors.BookSortedStrategy;
import by.example.bookstore_api.validator.BookValidationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private static final String DEFAULT_SORT_STRATEGY = "sortByPrice";
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final Map<String, BookSortedStrategy> bookSortedStrategies;
    private final BookValidationService bookValidationService;

    @Cacheable("bookCache")
    public BookResponseDto findById(UUID bookId) {
    return bookRepository
        .findById(bookId)
        .map(bookMapper::toBookDto)
        .orElseThrow(
            () -> new BookstoreException(String.format("Book with id %s not found", bookId)));
    }

    public Page<BookResponseDto> findAll(int page, int size, String filter, String sort) {
        Pageable pageable = PageRequest.of(page, size);

        BookSortedStrategy bookSortedStrategy = bookSortedStrategies.getOrDefault(sort,
                bookSortedStrategies.get(DEFAULT_SORT_STRATEGY));

        log.debug("Using sort strategy: {}", bookSortedStrategy.getClass().getSimpleName());

        Page<Book> books;

        if (filter != null && !filter.isEmpty()) {
            books = bookRepository.findAllByTitleContaining(filter, pageable);
        } else {
            books = bookRepository.findAll(pageable);
        }


        List<Book> sortedBooks = bookSortedStrategy.sortBooks(books.getContent());

    return new PageImpl<>(sortedBooks, pageable, books.getTotalElements())
        .map(bookMapper::toBookDto);
    }


    public void save(BookRequestDto bookRequestDto) {

        bookValidationService.validate(bookRequestDto);

        if (bookRepository.existsByTitleAndAuthorLastname(bookRequestDto.title(), bookRequestDto.authorLastName())) {
      throw new BookstoreException(
          String.format("Book with title %s already exists", bookRequestDto.title()));
        }
        bookRepository.save(bookMapper.toBook(bookRequestDto));
    }

    public void delete(UUID bookId) {
        bookRepository.deleteById(bookId);
    }


    @Transactional
    public void update(UUID bookId, BookRequestDto bookRequestDto) {

        bookValidationService.validate(bookRequestDto);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with id %s not found", bookId)));

        book.setTitle(bookRequestDto.title());
        book.setPrice(bookRequestDto.price());
        book.setPublishedYear(bookRequestDto.publishedYear());
        bookRepository.save(book);
    }

}
