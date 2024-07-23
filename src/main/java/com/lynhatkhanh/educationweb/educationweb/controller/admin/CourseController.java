package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/admin/course")
public class CourseController {

    private CourseService courseService;

    private UserAccountService userAccountService;

    @Autowired
    public CourseController(CourseService courseService, UserAccountService userAccountService) {
        this.courseService = courseService;
        this.userAccountService = userAccountService;
    }

    /* ============================== Course ============================== */
    @GetMapping("")
    public String showCourses(Model model,
                              @Param("keyword") String keyword, @RequestParam(value = "message", required = false) String message,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (message != null) {
            MessageUtil.showMessage(message, model);
        }

        Page<Course> listCourse = courseService.findAll(pageNo);

        if (keyword != null) {
            listCourse = courseService.searchByKeyword(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("totalPage", listCourse.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("courses", listCourse);

        return "admin/course";
    }

    @GetMapping("/showForm")
    public String showFormUpdateCourse(@RequestParam(value = "courseId", required = false) Integer theId, Model model) {

        Course theCourse = new Course();
        if (theId != null) {
            theCourse = courseService.findById(theId);
        }
        List<UserAccount> instructorList = userAccountService.getUsersOfRole(3);

        // send entity to template
        model.addAttribute("course", theCourse);
        model.addAttribute("instructors", instructorList);

        return "admin/form/course-form";
    }

    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course theCourse, BindingResult theBindingResult,
                             @RequestParam("instructorId-option") Integer instructorId, Model model) {
        String typeOfMessage = "";

        if (theBindingResult.hasErrors()) {
            List<UserAccount> instructorList = userAccountService.getUsersOfRole(3);
            model.addAttribute("course", theCourse);
            model.addAttribute("instructors", instructorList);
            return "admin/form/course-form";
        }
        else {
            if (theCourse.getId() != 0)
                typeOfMessage = "update_success";
            else
                typeOfMessage = "insert_success";
            theCourse.setInstructor(userAccountService.findById(instructorId));

        }

        // save entity
        courseService.save(theCourse);

        // use a redirect to /admin/course - to prevent duplicate submissions (reload confirmation site)
        return "redirect:/admin/course?message=" + typeOfMessage;
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int theId) {

        // delete entity
        courseService.deleteById(theId);

        // use a redirect to /admin/course - to prevent duplicate submissions (reload confirmation site)
        return "redirect:/admin/course";
    }

    @GetMapping("/showStudentOfCourse")
    public String showStudentOfCourse(Model model, @RequestParam(value = "message", required = false) String message,
                                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(value = "courseId") int courseId,
                                      @Param(value = "keyword") String keyword) {
        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<UserAccount> studentOfCoursePages = userAccountService.getStudentOfCourse(pageNo, courseId);

        model.addAttribute("courseId", courseId);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", studentOfCoursePages.getTotalPages());
        model.addAttribute("userAccounts", studentOfCoursePages);

        return "admin/list/user-list";
    }


    @GetMapping("/deleteOutOfCourse")
    public String deleteStudentOutOfCourse(@RequestParam("userId") int studentId, @RequestParam(value = "courseId") int courseId) {

        UserAccount theStudent = userAccountService.findById(studentId);
        theStudent.getEnrolledCourses().removeIf(instance -> instance.getCourse().getId()==courseId);
        theStudent.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        userAccountService.save(theStudent);

        return "redirect:/admin/course/showStudentOfCourse?courseId=" + courseId + "&message=delete_success";
    }

    @GetMapping("/showStudentDetail")
    public String showStudentDetail(@RequestParam("userId") int studentId, @RequestParam("courseId") int courseId, Model model) {

        UserAccount studentDetail = userAccountService.findById(studentId);

        model.addAttribute("userAccount", studentDetail);
        model.addAttribute("courseId", courseId);

        return "admin/detail/user-detail";
    }

    /* ============================== End-Course ============================== */
}
