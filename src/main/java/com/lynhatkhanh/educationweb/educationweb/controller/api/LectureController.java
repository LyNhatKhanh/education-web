package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dto.request.LectureRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.ApiResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.LectureResponse;
import com.lynhatkhanh.educationweb.educationweb.service.ILectureService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LectureController {

    ILectureService lectureService;

    @GetMapping("/{title}")
    public ApiResponse<LectureResponse> getLecture(@PathVariable("title") String title) {
        return ApiResponse.<LectureResponse>builder()
                .result(lectureService.getLecture(title))
                .build();
    }

    @GetMapping
    public ApiResponse<List<LectureResponse>> getLectures() {
        return ApiResponse.<List<LectureResponse>>builder()
                .result(lectureService.getLectures())
                .build();
    }

    @PostMapping
    public ApiResponse<LectureResponse> createLecture(@RequestBody LectureRequest request) {
        return ApiResponse.<LectureResponse>builder()
                .result(lectureService.createLecture(request))
                .build();
    }

    @PutMapping("/{title}")
    public ApiResponse<LectureResponse> updateLecture(@PathVariable("title") String title,
                                                      @RequestBody LectureRequest request) {
        return ApiResponse.<LectureResponse>builder()
                .result(lectureService.updateLecture(title, request))
                .build();
    }

    @DeleteMapping("/{title}")
    public ApiResponse<Void> deleteLecture(@PathVariable("title") String title) {
        LectureResponse lecture = lectureService.getLecture(title);
        lectureService.deleteLecture(title);
        return ApiResponse.<Void>builder()
                .message("Delete Lecture: " + lecture.getTitle() + " complete!")
                .build();
    }

}
