package com.company.domain.service.impl;

import com.company.domain.model.dto.ClassDtoHeader;
import com.company.domain.model.dto.ClassDto;
import com.company.domain.model.request.ClassRequest;
import com.company.domain.model.response.ClassResponse;
import com.company.domain.service.CategoryService;
import com.company.domain.service.ClassService;
import com.company.domain.service.ImageCompanyService;
import com.company.domain.service.ReviewService;
import com.company.persistence.entity.ClassEntity;
import com.company.persistence.repository.ClassRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2(topic = "CLASS-SERVICE")
@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ModelMapper mapper;
    private ReviewService reviewService;
    private final ImageCompanyService imageCompanyService;
    private final CategoryService categoryService;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository,
                            ModelMapper mapper,
                            ImageCompanyService imageCompanyService,
                            CategoryService categoryService) {
        this.classRepository = classRepository;
        this.mapper = mapper;
        this.imageCompanyService = imageCompanyService;
        this.categoryService = categoryService;
    }

    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Long addClass(ClassRequest classRequest) {
        var image = imageCompanyService.getImageCompany(classRequest.getIconTag());
        if (image ==  null) {
            log.info("Create new image");
            image = imageCompanyService.addImageCompany(classRequest.getIconTag());
        }
        var source = mapper.map(classRequest, ClassEntity.class);
        source.setImageId(image.getId());
        source.setRating(0);
        source.setCountOfReviews(0);

        try {
            categoryService.findById(source.getCategoryId());
        }
        catch (EntityNotFoundException ex) {
            log.error("Unable to save class");
            throw new EntityNotFoundException(source.getCategoryId().toString());
        }

        var result = classRepository.save(source);
        categoryService.updateCategoryAfterInsertClass(result.getCategoryId());
        return result.getId();
    }

    @Override
    @Transactional
    public ClassResponse getClass(Long id) {
        var source = classRepository.getsClassById(id);
        if (source == null) {
            log.error("Unable to find entity with id " + id);
            throw new EntityNotFoundException("id " + id);
        }
        else {
            var classDto = mapper.map(source, ClassDtoHeader.class);
            var reviews = reviewService.getAll(id);
            return ClassResponse.builder()
                .classDto(classDto)
                .reviews(reviews)
                .build();
        }
    }

    @Override
    public ClassEntity findById(Long id) {
        return classRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @Override
    @Transactional
    public List<ClassDto> getAll() {
        var source = classRepository.getsClasses();
        return source.stream()
            .map(e -> mapper.map(e, ClassDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateClassAfterInsertReview(Long id) {
        classRepository.updateCountOfReviewsAndRating(id);
    }
}

