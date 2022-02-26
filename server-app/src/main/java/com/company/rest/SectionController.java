package com.company.rest;

import com.company.domain.model.response.SectionResponse;
import com.company.domain.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/section")
public class SectionController {

    private final SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<SectionResponse> findCategoriesById(Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sectionService.findCategoriesById(id));
    }

    @GetMapping
    public ResponseEntity<List<SectionResponse>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAll());
    }

    @GetMapping("bar/{id}")
    public ResponseEntity<SectionResponse> findCategoriesByIdBar(Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sectionService.findCategoriesById(id));
    }

    @GetMapping("/bar")
    public ResponseEntity<List<SectionResponse>> findCategoriesBar() {
        return ResponseEntity.ok(sectionService.getAll());
    }
}
