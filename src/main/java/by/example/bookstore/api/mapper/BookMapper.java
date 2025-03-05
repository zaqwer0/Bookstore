package by.example.bookstore.api.mapper;


import by.example.bookstore.api.model.dto.request.BookRequestDto;
import by.example.bookstore.api.model.dto.response.BookResponseDto;
import by.example.bookstore.api.model.entity.Book;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

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
