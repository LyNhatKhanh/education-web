package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.CourseRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.LectureRepository;
import com.lynhatkhanh.educationweb.educationweb.dto.request.LectureRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.LectureResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Course;
import com.lynhatkhanh.educationweb.educationweb.entity.Lecture;
import com.lynhatkhanh.educationweb.educationweb.exception.AppException;
import com.lynhatkhanh.educationweb.educationweb.exception.ErrorCode;
import com.lynhatkhanh.educationweb.educationweb.mapper.LectureMapper;
import com.lynhatkhanh.educationweb.educationweb.service.ILectureService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LectureServiceImpl implements ILectureService {

    LectureRepository lectureRepository;
    CourseRepository courseRepository;
    LectureMapper lectureMapper;

    @Override
    public LectureResponse getLecture(String title) {
        Lecture lecture = lectureRepository.findById(title)
                .orElseThrow(() -> new AppException(ErrorCode.LECTURE_NOT_EXISTED));
        return lectureMapper.toLectureResponse(lecture);
    }

    @Override
    public List<LectureResponse> getLectures() {
        List<Lecture> lectureList = lectureRepository.findAll();
        return lectureList.stream()
                .map(lecture -> lectureMapper.toLectureResponse(lecture))
                .collect(Collectors.toList());
    }

    @Override
    public LectureResponse createLecture(LectureRequest request) {
        if (lectureRepository.existsByTitle(request.getTitle()))
            throw new AppException(ErrorCode.LECTURE_EXISTED);

        Lecture lecture = lectureMapper.toLecture(request);
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXISTED));
        lecture.setCourseId(course);
        lecture = lectureRepository.save(lecture);
        return lectureMapper.toLectureResponse(lecture);
    }

    @Override
    public LectureResponse updateLecture(String title, LectureRequest request) {
        Lecture lecture = lectureRepository.findById(title)
                .orElseThrow(() -> new AppException(ErrorCode.LECTURE_NOT_EXISTED));
        lectureMapper.updateLecture(lecture, request);
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXISTED));
        lecture.setCourseId(course);
        lecture = lectureRepository.save(lecture);
        return lectureMapper.toLectureResponse(lecture);
    }

    @Override
    public void deleteLecture(String title) {
        lectureRepository.deleteById(title);
    }
}
