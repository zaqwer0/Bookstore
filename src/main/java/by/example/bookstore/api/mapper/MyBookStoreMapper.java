package by.example.bookstore.api.mapper;

import by.example.bookstore.api.model.dto.request.MyBookstoreRequestDto;
import by.example.bookstore.api.model.dto.response.MyBookstoreResponseDto;
import by.example.bookstore.api.model.entity.MyBookstore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MyBookStoreMapper {

    @Mapping(target = "book.id", source = "bookId")
    MyBookstore toBookstore(MyBookstoreRequestDto myBookstoreRequestDto);

    @Mapping(target = "bookTitle", source = "book.title")
    MyBookstoreResponseDto toBookstoreResponse(MyBookstore myBookstore);

}
