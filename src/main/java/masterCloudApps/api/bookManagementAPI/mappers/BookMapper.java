package masterCloudApps.api.bookManagementAPI.mappers;

import masterCloudApps.api.bookManagementAPI.dto.BookDto;
import masterCloudApps.api.bookManagementAPI.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    BookDto toDto(Book book);
    /*List<BookDto> toDtos(Collection<Book> books);
    Book toDomain(BookDto bookDto);*/
}
