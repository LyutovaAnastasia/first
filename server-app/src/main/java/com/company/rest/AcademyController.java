package com.company.rest;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.service.AcademyService;
import com.company.persistence.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academy")
public class AcademyController {

    private final AcademyService academyService;
    private final AcademyRepository academyRepository;

    @GetMapping("/{id}")
    public ResponseEntity<AcademyDto> getAcademy(@PathVariable("id") Long id) {
        return ResponseEntity.ok(academyService.getAcademy(id));
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

    @GetMapping
    public ResponseEntity<List<AcademyDto>> getAcademy() {
        return ResponseEntity.ok(academyService.getAll());
    }
}
