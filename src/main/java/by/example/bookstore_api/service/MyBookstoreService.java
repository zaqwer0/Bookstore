package by.example.bookstore_api.service;

import by.example.bookstore_api.model.dto.request.MyBookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.MyBookstoreResponseDto;
import java.util.UUID;

public interface MyBookstoreService {

  MyBookstoreResponseDto findByBookId(UUID bookId);

  void save(MyBookstoreRequestDto myBookstoreRequestDto);

  void delete(UUID bookId);

  void update(UUID bookId, MyBookstoreRequestDto myBookstoreRequestDto);
}
