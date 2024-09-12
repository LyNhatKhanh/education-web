package com.lynhatkhanh.educationweb.educationweb.mapper;

import com.lynhatkhanh.educationweb.educationweb.dto.request.LectureRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.LectureResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Lecture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LectureMapper {

    @Mapping(target = "courseId", ignore = true)
    Lecture toLecture(LectureRequest request);

    LectureResponse toLectureResponse(Lecture lecture);

    @Mapping(target = "courseId", ignore = true)
    void updateLecture(@MappingTarget Lecture lecture, LectureRequest request);
}
