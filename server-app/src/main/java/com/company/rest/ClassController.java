package com.company.rest;

import com.company.domain.model.dto.ClassDtoHeader;
import com.company.domain.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/class")
public class ClassController {

    private final ClassService classService;

    @GetMapping("/{id}")
    public ResponseEntity<ClassDtoHeader> getClass(@PathVariable("id") Long id) {
        return ResponseEntity.ok(classService.getClass(id));
    }

}
