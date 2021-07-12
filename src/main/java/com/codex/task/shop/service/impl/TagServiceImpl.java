package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.entity.EntityIsExistException;
import com.codex.task.shop.model.dto.TagCreationDto;
import com.codex.task.shop.model.entity.Tag;
import com.codex.task.shop.repository.mysql.TagRepository;
import com.codex.task.shop.service.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    ModelMapper modelMapper;
    TagRepository tagRepository;

    @Override
    public void createTag(TagCreationDto tagDto) {
        if (tagRepository.existsByValue(tagDto.getValue())) {
            log.info("tag with value {} is  exist", tagDto.getValue());
            throw new EntityIsExistException("tag with value is exist: " + tagDto.getValue());
        }
        Tag tag = modelMapper.map(tagDto, Tag.class);
        log.info("try to save tag");
        tagRepository.save(tag);
    }

}
