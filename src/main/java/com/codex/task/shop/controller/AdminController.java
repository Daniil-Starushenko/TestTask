package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.TagCreationDto;
import com.codex.task.shop.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    TagService tagService;

    @PostMapping("/product")
    public String createProduct() {
        return "hello";
    }

    @PostMapping("/tag")
    public void createTag(@RequestBody TagCreationDto tagDto) {
        tagService.createTag(tagDto);
    }

}
