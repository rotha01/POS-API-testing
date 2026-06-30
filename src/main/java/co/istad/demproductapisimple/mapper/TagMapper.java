package co.istad.demproductapisimple.mapper;

import co.istad.demproductapisimple.dto.Tags.TagRequest;
import co.istad.demproductapisimple.dto.Tags.TagResponse;
import co.istad.demproductapisimple.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponse toResponse(Tag tag);
    Tag toEntity(TagRequest tagRequest);
}