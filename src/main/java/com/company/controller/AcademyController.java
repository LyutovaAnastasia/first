package com.company.controller;

import com.company.model.dto.AcademyDto;
import com.company.model.response.AcademyResponse;
import com.company.model.response.SectionResponse;
import com.company.persistence.entity.AcademyEntity;
import com.company.service.AcademyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academy")
@RequiredArgsConstructor
public class AcademyController {

    private final AcademyService academyService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AcademyResponse>> findAcademyById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(academyService.findCategoryById(id));
    }
}
