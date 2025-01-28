package by.example.bookstore_api.service;

import by.example.bookstore_api.mapper.BookMapper;
import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.repository.BookRepository;
import by.example.bookstore_api.strategy.BookSortedStrategy;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final Map<String, BookSortedStrategy> bookSortedStrategies;
    private final BookValidationService bookValidationService;

    public List<BookResponseDto> findAllSorted(String sortType) {

        List<Book> books = bookRepository.findAll();

        BookSortedStrategy strategy = bookSortedStrategies.get(sortType);

        //todo bad practice, better to provide default logic
        if (strategy == null) {
            throw new IllegalArgumentException(
                    String.format("Sorting strategy '%s' not found", sortType)
            );
        }
        books = strategy.sortBooks(books);

        return bookMapper.toBooksResponse(books);
    }

    public List<Book> findBooksWithSorting(String field) {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Cacheable("bookCache")
    public BookResponseDto findById(UUID bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with id %s not found", bookId)));
    }

    public Page<BookResponseDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookRepository.findAll(pageable);
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


    //todo why do we need this ChatGPT-type comment??
    // there could be implemented logic that will be update for example
    // changing author name or bookstore name
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
