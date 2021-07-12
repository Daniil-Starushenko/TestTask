package com.codex.task.shop.service;

import com.codex.task.shop.model.dto.TagCreationDto;
import com.codex.task.shop.model.entity.Tag;

public interface TagService {

    void createTag(TagCreationDto tagDto);

}
