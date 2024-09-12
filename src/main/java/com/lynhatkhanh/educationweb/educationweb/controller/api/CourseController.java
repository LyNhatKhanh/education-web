package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dto.request.CourseRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.ApiResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.CourseResponse;
import com.lynhatkhanh.educationweb.educationweb.service.ICourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CourseController {

    ICourseService courseService;

    @GetMapping("/{name}")
    public ApiResponse<CourseResponse> getRole(@PathVariable("name") String name) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.getCourse(name))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CourseResponse>> getRoles() {
        return ApiResponse.<List<CourseResponse>>builder()
                .result(courseService.getCourses())
                .build();
    }

    @PostMapping
    public ApiResponse<CourseResponse> createRole(@RequestBody CourseRequest request) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.createCourse(request))
                .build();
    }

    @PutMapping("/{name}")
    public ApiResponse<CourseResponse> updateRole(@PathVariable("name") String name,
                                                  @RequestBody CourseRequest request) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.updateCourse(name, request))
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse<Void> deleteRole(@PathVariable("name") String name) {
        CourseResponse course = courseService.getCourse(name);
        courseService.deleteCourse(name);
        return ApiResponse.<Void>builder()
                .message("Delete course: " + course.getTitle() + " complete!")
                .build();
    }
}
