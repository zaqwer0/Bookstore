package by.example.bookstore_api.mapper;

import by.example.bookstore_api.model.dto.request.BookstoreRequestDto;
import by.example.bookstore_api.model.dto.response.BookstoreResponseDto;
import by.example.bookstore_api.model.entity.Bookstore;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookStoreMapper {
    Bookstore toBookstore(BookstoreRequestDto bookstoreRequestDto);

    List<BookstoreResponseDto> toBookstoresResponse(List<Bookstore> bookstoreDttos);

    BookstoreResponseDto toBookstoreDto(Bookstore bookstore);

}
