package com.company.rest;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.service.AcademyService;
import com.company.persistence.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/academy")
public class AcademyController {

    private final AcademyService academyService;

    @GetMapping("/{id}")
    public ResponseEntity<AcademyDto> getAcademy(@PathVariable("id") Long id) {
        return ResponseEntity.ok(academyService.getAcademy(id));
    }

    @GetMapping()
    public ResponseEntity<List<AcademyDto>> getAllAcademies() {
        return ResponseEntity.ok(academyService.getAll());
    }

    @PutMapping
    public ResponseEntity<Long> addAcademy(@RequestBody AcademyDto academyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(academyService.addAcademy(academyDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> updateAcademy(@RequestBody AcademyDto academyDto) {
        academyService.updateAcademy(academyDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademy(@PathVariable("id") Long id) {
        academyService.deleteAcademy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("page/{id}")
    public ResponseEntity<Page<AcademyDto>> getAllAcademiesPage(@PathVariable("id") Long id,
           @RequestParam(value = "page", required = false) Integer page,
           @RequestParam(value = "size", required = false) Integer size                                                  ) {
        return ResponseEntity.ok(academyService.getAllPage(id, PageRequest.of(page, size)));
    }

}
