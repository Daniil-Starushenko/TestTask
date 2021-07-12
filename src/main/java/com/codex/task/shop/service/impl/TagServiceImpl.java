package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.entity.EntityIsExistException;
import com.codex.task.shop.model.dto.TagCreateDto;
import com.codex.task.shop.model.entity.Tag;
import com.codex.task.shop.repository.mysql.TagRepository;
import com.codex.task.shop.service.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private ModelMapper modelMapper;

    private TagRepository tagRepository;

    @Override
    public void createTag(TagCreateDto tagDto) {
        String tagValue = tagDto.getValue().toUpperCase();
        if (tagRepository.existsByValue(tagValue)) {
            log.info("tag with value {} is  exist", tagValue);
            throw new EntityIsExistException("tag with value is exist: " + tagValue);
        }
        Tag tag = modelMapper.map(tagDto, Tag.class);
        log.info("try to save tag");
        tagRepository.save(tag);
    }

}
