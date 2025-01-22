package by.example.bookstore_api.mapper;

import by.example.bookstore_api.model.dto.request.BookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.BookstoreResponseDto;
import by.example.bookstore_api.model.entity.Bookstore;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookStoreMapper {
    Bookstore toBookstore(BookstoreRequestDto bookstoreRequestDto);

    Bookstore toBookstore(BookstoreResponseDto bookstoreResponseDto);

    List<BookstoreResponseDto> toBookstoresResponse(List<Bookstore> bookstoreDttos);

    BookstoreResponseDto toBookstoreDto(Bookstore bookstore);

}
