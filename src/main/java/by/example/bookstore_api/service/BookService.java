package by.example.bookstore_api.service;

import by.example.bookstore_api.mapper.BookMapper;
import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.model.entity.Book;
import by.example.bookstore_api.repository.AuthorRepository;
import by.example.bookstore_api.repository.BookRepository;
import by.example.bookstore_api.repository.BookstoreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final BookstoreRepository bookstoreRepository;

    public BookResponseDto findById(UUID bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with id %s not found", bookId)));
    }

    public List<BookResponseDto> findAll() {
        return bookMapper.toBooksResponse(bookRepository.findAll());
    }

    public void save(BookRequestDto bookRequestDto) {
        if (bookRepository.existsByTitleAndAuthor_LastName(bookRequestDto.title(), bookRequestDto.authorLastName())) {
            throw new IllegalArgumentException(String.format("Book with title %s already exists", bookRequestDto.title()));
        }
        bookRepository.save(bookMapper.toBook(bookRequestDto));
    }

    public void delete(UUID bookId) {
        bookRepository.deleteById(bookId);
    }

    // there could be implemented logic that will be update for example
    // changing author name or bookstore name
    @Transactional
    public void update(UUID bookId, BookRequestDto bookRequestDto) {
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
