package com.company.domain.service;

import com.company.domain.model.dto.ReviewDto;
import com.company.domain.model.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    Long addReview(ReviewRequest reviewRequest);
    void updateReview(ReviewDto reviewDto);
    List<ReviewDto> getAll(Long id);
    List<ReviewDto> getAllActive();
    List<ReviewDto> getAllNoActive();
}
