package by.example.bookstore_api.service.impl;

import by.example.bookstore_api.mapper.BookMapper;
import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.repository.BookRepository;
import by.example.bookstore_api.service.BookService;
import by.example.bookstore_api.strategy.BookSortedStrategy;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final Map<String, BookSortedStrategy> bookSortedStrategies;
    private final BookValidationService bookValidationService;

    private static final String DEFAULT_SORT_STRATEGY = "sortByPrice";

    public List<BookResponseDto> findAllSorted(String sortType) {

        List<Book> books = bookRepository.findAll();

        BookSortedStrategy bookSortedStrategy = bookSortedStrategies.getOrDefault(sortType,
                bookSortedStrategies.get(DEFAULT_SORT_STRATEGY));

        log.debug("Using default sort strategy: {}", bookSortedStrategy.getClass().getSimpleName());

        return bookMapper.toBooksResponse(bookSortedStrategy.sortBooks(books));
    }

    @Cacheable("bookCache")
    public BookResponseDto findById(UUID bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with id %s not found", bookId)));
    }

    public Page<BookResponseDto> findAll(int page, int size, String filter) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books;

        if (filter != null && !filter.isEmpty()) {

            books = bookRepository.findAllByTitleContaining(filter, (java.awt.print.Pageable) pageable);
        } else {
            books = bookRepository.findAll(pageable);
        }

        return books.map(bookMapper::toBookDto);
    }


    public void save(BookRequestDto bookRequestDto) {

        bookValidationService.validate(bookRequestDto);

        if (bookRepository.existsByTitleAndAuthorLastname(bookRequestDto.title(), bookRequestDto.authorLastName())) {
            throw new IllegalArgumentException(String.format("Book with title %s already exists", bookRequestDto.title()));
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

    public BookResponseDto findByTitle(String title) {
        return bookRepository.findByTitle(title)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with title %s not found", title)));
    }

}
