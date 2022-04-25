package com.company.rest;

import com.company.domain.model.dto.ReviewDto;
import com.company.domain.model.request.ReviewRequest;
import com.company.domain.service.ReviewService;
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
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Long> addReview(@RequestBody ReviewRequest reviewRequest) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(reviewRequest));
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateAcademy(@RequestBody ReviewDto reviewDto) throws MethodArgumentTypeMismatchException {
        reviewService.updateReview(reviewDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/admin/active")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReviewDto>> getAllActiveReviews() {
        return ResponseEntity.ok(reviewService.getAllActive());
    }

    @GetMapping("/admin/proactive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ReviewDto>> getAllNoActiveReviews() {
        return ResponseEntity.ok(reviewService.getAllNoActive());
    }
}
