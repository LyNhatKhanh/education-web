package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.model.Lecture;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import com.lynhatkhanh.educationweb.educationweb.service.LectureService;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
                              @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "keyword", required = false) String keyword) {

        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<Lecture> lecturePage = lectureService.findAll(pageNo);

        if (keyword != null) {
            lecturePage = lectureService.searchByKeyword(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("totalPage", lecturePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("lectures", lecturePage);

        return "admin/lecture";
    }

    @GetMapping("/form")
    public String showForm(Model model, @RequestParam(value = "lectureId", required = false) Integer lectureId) {
        Lecture lecture = new Lecture();
        if (lectureId != null)
            lecture = lectureService.findById(lectureId);
        List<Course> courses = courseService.findAll();

        model.addAttribute("lecture", lecture);
        model.addAttribute("courses", courses);

        return "admin/form/lecture-form";
    }

    @PostMapping("/save")
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
