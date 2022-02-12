package com.company.controller;

import com.company.model.response.SectionResponse;
import com.company.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<SectionResponse> findCategoriesById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sectionService.findCategoriesById(id));
    }

    @GetMapping()
    public ResponseEntity<List<SectionResponse>> findCategories() {
        return ResponseEntity.ok(sectionService.findCategories());
    }

    @GetMapping("bar/{id}")
    public ResponseEntity<SectionResponse> findCategoriesByIdBar(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sectionService.findCategoriesById(id));
    }

    @GetMapping("/bar")
    public ResponseEntity<List<SectionResponse>> findCategoriesBar() {
        return ResponseEntity.ok(sectionService.findCategories());
    }
}
