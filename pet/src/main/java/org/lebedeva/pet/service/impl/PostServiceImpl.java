package org.lebedeva.pet.service.impl;

import lombok.NonNull;
import org.lebedeva.pet.dto.post.PostDto;
import org.lebedeva.pet.mapper.post.PostMapper;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.model.post.Post;
import org.lebedeva.pet.repository.CategoryRepository;
import org.lebedeva.pet.repository.PostRepository;
import org.lebedeva.pet.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(CategoryRepository categoryRepository,
                           PostRepository postRepository,
                           PostMapper postMapper) {
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Post save(PostDto dto) {
        Set<Category> categories = dto.getCategoryId().stream()
                .map(id -> categoryRepository.findById(id).orElse(null))
                .collect(Collectors.toSet());

        Post post = postMapper.toEntity(dto);
        post.setCategories(categories);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<PostDto> findById(Integer id) {
        return postRepository.findById(id)
                .map(postMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<PostDto> findAllByContainsTitle(@NonNull @NotNull String title, Pageable pageable) {
        return postRepository.findAllByTitleContainsIgnoreCase(title, pageable)
                .map(postMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<PostDto> findAllByCategory(@NonNull @NotNull Category category, Pageable pageable) {
        return postRepository.findAllByCategories(category, pageable)
                .map(postMapper::toDto);
    }
}
