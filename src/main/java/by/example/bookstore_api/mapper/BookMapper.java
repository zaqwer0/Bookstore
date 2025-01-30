package by.example.bookstore_api.mapper;


import by.example.bookstore_api.model.dto.request.BookRequestDto;
import by.example.bookstore_api.model.dto.response.BookResponseDto;
import by.example.bookstore_api.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    Book toBook(BookResponseDto authorResponseDto);

    Book toBook(BookRequestDto authorRequestDto);

    List<BookResponseDto> toBooksResponse(List<Book> bookDtos);


    BookResponseDto toBookDto(Book book);
}
