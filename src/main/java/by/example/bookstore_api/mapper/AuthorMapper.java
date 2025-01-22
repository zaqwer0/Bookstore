package by.example.bookstore_api.mapper;

import by.example.bookstore_api.model.dto.request.AuthorRequestDto;
import by.example.bookstore_api.model.dto.response.AuthorResponseDto;
import by.example.bookstore_api.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {

    Author toAuthor(AuthorResponseDto authorResponseDto);

    Author toAuthor(AuthorRequestDto authorRequestDto);

    List<AuthorResponseDto> toAuthors(List<Author> authorDtos);


    AuthorResponseDto toAuthorDto(Author author);
}
