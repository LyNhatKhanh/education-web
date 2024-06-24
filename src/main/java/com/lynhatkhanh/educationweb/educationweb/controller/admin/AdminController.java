package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.model.Instructor;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import com.lynhatkhanh.educationweb.educationweb.service.InstructorService;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CourseService courseService;
    private InstructorService instructorService;

    @Autowired
    public AdminController(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @GetMapping({"/index", "/index.html", ""})
    public String showIndex() {
        return "admin/index";
    }

    @GetMapping("/dashboard")
    public String showDashBoard() {
        return "admin/dashboard";
    }


     /* ============================== Course ============================== */
    @GetMapping("/course")
    public String showCourse(Model theModel,
                             @Param("keyword") String keyword,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        Page<Course> listCourse = courseService.getAll(pageNo);

        if (keyword != null) {
            listCourse = courseService.searchCourse(keyword, pageNo);
            theModel.addAttribute("keyword", keyword);
        }

        theModel.addAttribute("totalPage", listCourse.getTotalPages());
        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("courses", listCourse);

        return "admin/course";
    }

    @GetMapping("/course/showFormForAdd")
    public String showFormAddCourse(Model theModel) {

        Course theCourse = new Course();
        List<Instructor> listInstructor = instructorService.findAll();

        theModel.addAttribute("course", theCourse);
        theModel.addAttribute("instructors", listInstructor);

        return "admin/form/course-form";
    }

    @GetMapping("/course/showFormForUpdate")
    public String showFormUpdateCourse(@RequestParam("courseId") int theId, Model theModel) {

        List<Instructor> listInstructor = instructorService.findAll();

        // find entity
        Course theCourse = courseService.findById(theId);

        // send entity to template
        theModel.addAttribute("course", theCourse);
        theModel.addAttribute("instructors", listInstructor);

        return "admin/form/course-form";
    }

    @PostMapping("/course/save")
    public String saveCourse(@ModelAttribute("course") Course theCourse) {

        // save entity
        courseService.save(theCourse);

        // use a redirect to /admin/course - to prevent duplicate submissions (reload confirmation site)
        return "redirect:/admin/course";
    }

    @GetMapping("/course/deleteEmployee")
    public String deleteCourse(@RequestParam("courseId") int theId) {

        // delete entity
        courseService.deleteById(theId);

        // use a redirect to /admin/course - to prevent duplicate submissions (reload confirmation site)
        return "redirect:/admin/course";
    }




    /* ============================== Course ============================== */










}
