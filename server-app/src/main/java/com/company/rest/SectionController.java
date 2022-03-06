package com.company.rest;

import com.company.domain.model.response.SectionResponse;
import com.company.domain.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/section")
public class SectionController {

    private final SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<SectionResponse> getSection(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sectionService.getSection(id));
    }

    @GetMapping
    public ResponseEntity<List<SectionResponse>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAll());
    }

    @GetMapping("bar/{id}")
    public ResponseEntity<SectionResponse> getSectionBar(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sectionService.getSection(id));
    }

    @GetMapping("/bar")
    public ResponseEntity<List<SectionResponse>> getAllSectionsBar() {
        return ResponseEntity.ok(sectionService.getAll());
    }
}
