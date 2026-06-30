package co.istad.demproductapisimple.service.tag;

import co.istad.demproductapisimple.dto.Tags.TagRequest;
import co.istad.demproductapisimple.dto.Tags.TagResponse;
import co.istad.demproductapisimple.mapper.TagMapper;
import co.istad.demproductapisimple.repository.TagRepository;
import co.istad.demproductapisimple.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagResponse createTag(TagRequest request) {
        var tag =  tagMapper.toEntity(request);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    @Override
    public Page<TagResponse> getAllTags(Pageable pageable) {
        return  tagRepository
                .findAll(pageable)
                .map(tagMapper::toResponse);
    }
}
