package com.company.rest;

import com.company.domain.model.dto.ClassDto;
import com.company.domain.model.request.ClassRequest;
import com.company.domain.model.response.ClassResponse;
import com.company.domain.service.ClassService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/class")
public class ClassController {

    private final ClassService classService;

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ClassResponse> getClass(@PathVariable("id") Long id)
        throws MethodArgumentTypeMismatchException, EntityNotFoundException {

        return ResponseEntity.ok(classService.getClass(id));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ClassDto>> getAllClasses() {
        return ResponseEntity.ok(classService.getAll());
    }


    @GetMapping("")
    public ResponseEntity<List<ClassDto>> getAll() {
        return ResponseEntity.ok(classService.getAll());
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Long> addClass(@RequestBody ClassRequest classRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.addClass(classRequest));
    }
}
