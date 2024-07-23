package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.model.Lecture;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import com.lynhatkhanh.educationweb.educationweb.service.LectureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/lecture")
public class LectureController {

    private LectureService lectureService;

    private CourseService courseService;

    @Autowired
    public LectureController(LectureService lectureService, CourseService courseService) {
        this.lectureService = lectureService;
        this.courseService = courseService;
    }

    /* ============================== Lecture ============================== */

    @GetMapping("")
    public String showLecture(Model model, @RequestParam(value = "message", required = false) String message,
                              @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        /* TODO: set findAll(pageable) */
        List<Lecture> lectures = lectureService.findAll();

        model.addAttribute("lectures", lectures);

        return "admin/lecture";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdateLecture(Model model, @RequestParam("lectureId") int lectureId) {

        Lecture lecture = lectureService.findById(lectureId);
        List<Course> courses = courseService.findAll();

        model.addAttribute("lecture", lecture);
        model.addAttribute("courses", courses);

        return "admin/form/lecture-form";
    }

    @GetMapping("/save")
    public String saveLecture(@Valid @ModelAttribute("lecture") Lecture lecture, BindingResult bindingResult, Model model,
                              @RequestParam("courseId-option") int courseId) {

        String typeOfMessage = null;

        if (bindingResult.hasErrors()) {
            List<Course> courses = courseService.findAll();

            model.addAttribute("lecture", lecture);
            model.addAttribute("courses", courses);
            return "admin/form/lecture-form";
        } else {
            if (lecture.getId() != 0)
                typeOfMessage = "update_success";
            else
                typeOfMessage = "insert_success";
            lecture.setCourseId(courseService.findById(courseId));
            lectureService.save(lecture);
            return "redirect:/admin/lecture?message=" + typeOfMessage;
        }
    }


    /* ============================== End-Lecture ============================== */

}
