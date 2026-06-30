package co.istad.demproductapisimple.service.tag;

import co.istad.demproductapisimple.dto.Tags.TagRequest;
import co.istad.demproductapisimple.dto.Tags.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    // request, response
    TagResponse createTag(TagRequest request);
    Page<TagResponse> getAllTags(Pageable pageable);
}
