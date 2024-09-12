package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.LectureRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.LectureResponse;

import java.util.List;

public interface ILectureService {
    LectureResponse getLecture(String title);
    List<LectureResponse> getLectures();
    LectureResponse createLecture(LectureRequest request);
    LectureResponse updateLecture(String title, LectureRequest request);
    void deleteLecture(String title);
}
