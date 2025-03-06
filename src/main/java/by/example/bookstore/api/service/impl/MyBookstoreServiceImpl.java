package by.example.bookstore.api.service.impl;

import by.example.bookstore.api.exception.BookstoreException;
import by.example.bookstore.api.mapper.MyBookStoreMapper;
import by.example.bookstore.api.model.dto.request.MyBookstoreRequestDto;
import by.example.bookstore.api.model.dto.response.MyBookstoreResponseDto;
import by.example.bookstore.api.model.entity.Book;
import by.example.bookstore.api.model.entity.MyBookstore;
import by.example.bookstore.api.repository.BookRepository;
import by.example.bookstore.api.repository.MyBookstoreRepository;
import by.example.bookstore.api.service.MyBookstoreService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBookstoreServiceImpl implements MyBookstoreService {

  private final MyBookstoreRepository myBookstoreRepository;
  private final BookRepository bookRepository;
  private final MyBookStoreMapper myBookStoreMapper;

  public MyBookstoreResponseDto findByBookId(UUID bookId) {
    MyBookstore myBookstore =
        myBookstoreRepository
            .findById(bookId)
            .orElseThrow(() -> new BookstoreException("Book not found"));

    return myBookStoreMapper.toBookstoreResponse(myBookstore);
  }

  public void save(MyBookstoreRequestDto myBookstoreRequestDto) {
    Book book =
        bookRepository
            .findById(myBookstoreRequestDto.bookId())
            .orElseThrow(
                () ->
                    new BookstoreException(
                        "Book not found with id " + myBookstoreRequestDto.bookId()));

    MyBookstore myBookstore =
        MyBookstore.builder().book(book).quantity(myBookstoreRequestDto.quantity()).build();

    myBookstoreRepository.save(myBookstore);
  }

  public void delete(UUID bookId) {
    MyBookstore myBookstore =
        myBookstoreRepository
            .findByBookId(bookId)
            .orElseThrow(() -> new BookstoreException("Book not found with id " + bookId));

    myBookstoreRepository.delete(myBookstore);
  }

  @Transactional
  public void update(UUID bookId, MyBookstoreRequestDto myBookstoreRequestDto) {

    MyBookstore myBookstore =
        myBookstoreRepository
            .findById(bookId)
            .orElseThrow(
                () -> new BookstoreException("Bookstore with id " + bookId + " not found"));

    myBookstore.setQuantity(myBookstoreRequestDto.quantity());

    myBookstoreRepository.save(myBookstore);
  }
}
