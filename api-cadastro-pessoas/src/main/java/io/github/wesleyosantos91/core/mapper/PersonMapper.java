package io.github.wesleyosantos91.core.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import io.github.wesleyosantos91.api.v1.request.PersonQueryRequest;
import io.github.wesleyosantos91.api.v1.request.PersonRequest;
import io.github.wesleyosantos91.api.v1.response.PersonResponse;
import io.github.wesleyosantos91.domain.entity.Person;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PersonMapper {

    Person toEntity(PersonQueryRequest queryRequest);

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonRequest request);

    PersonResponse toResponse(Person person);

    @Mapping(target = "id", ignore = true)
    Person toEntity(Person source, @MappingTarget Person target);

    default List<PersonResponse> toListResponse(List<Person> entities) {
        final List<PersonResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<PersonResponse> toPageResponse(Page<Person> pages) {
        final List<PersonResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());
    }
}
