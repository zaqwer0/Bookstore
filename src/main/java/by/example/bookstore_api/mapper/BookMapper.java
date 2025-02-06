package by.example.bookstore_api.mapper;


import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    Book toBook(BookResponseDto authorResponseDto);

    Book toBook(BookRequestDto authorRequestDto);

    List<BookResponseDto> toBooksResponse(List<Book> bookDtos);

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.lastname", target = "authorLastName")
    //@Mapping(source = "myBookstore.name", target = "bookstoreName")
    BookResponseDto toBookDto(Book book);
}
