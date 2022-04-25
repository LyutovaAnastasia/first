package com.company.domain.service.impl;

import com.company.domain.model.dto.ReviewDto;
import com.company.domain.model.request.ReviewRequest;
import com.company.domain.service.ClassService;
import com.company.domain.service.ReviewService;
import com.company.persistence.entity.ReviewEntity;
import com.company.persistence.repository.ReviewRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2(topic = "REVIEW-SERVICE")
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper mapper;
    private ClassServiceImpl classService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper mapper, ClassServiceImpl classService) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
        this.classService = classService;
    }

    @PostConstruct
    public void init() {
        classService.setReviewService(this);
    }

    public ClassServiceImpl getClassService() {
        return classService;
    }

//    @Autowired
//    public void setClassService(ClassService classService) {
//        this.classService = classService;
//    }
//
//    public ClassService getClassService() {
//        return classService;
//    }

    @Override
    public Long addReview(ReviewRequest reviewRequest) {
        var source = mapper.map(reviewRequest, ReviewEntity.class);
        source.setActive(false);

//        if (classService.findById(source.getClassId()) == null) {
//            log.error("Unable to save review");
//            throw new EntityNotFoundException(source.getClassId().toString());
//        }
//        else {
//            var result = reviewRepository.save(source);
//            classService.updateClassAfterInsertReview(result.getClassId());
//            return result.getId();
//        }

        try {
           classService.findById(source.getClassId());
        }
        catch (EntityNotFoundException ex) {
            log.error("Unable to save review");
            throw new EntityNotFoundException("id " + source.getClassId());
        }
        var result = reviewRepository.save(source);
        classService.updateClassAfterInsertReview(result.getClassId());
        return result.getId();
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        var source = mapper.map(reviewDto, ReviewEntity.class);
        reviewRepository.save(source);
    }

    @Override
    @Transactional
    public List<ReviewDto> getAll(Long id) {
        var source = reviewRepository.getsReviewsByClassId(id);
        List<ReviewDto> result = source.stream()
            .map(e -> mapper.map(e, ReviewDto.class))
            .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ReviewDto> getAllActive() {
        var source = reviewRepository.getsReviewsActive();
        return source.stream()
            .map(e -> mapper.map(e, ReviewDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllNoActive() {
        var source = reviewRepository.getsReviewsNoActive();
        return source.stream()
            .map(e -> mapper.map(e, ReviewDto.class))
            .collect(Collectors.toList());
    }

}
