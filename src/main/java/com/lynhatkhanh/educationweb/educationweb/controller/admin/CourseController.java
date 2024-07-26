package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.model.CourseUser;
import com.lynhatkhanh.educationweb.educationweb.model.Lecture;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import com.lynhatkhanh.educationweb.educationweb.service.LectureService;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
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
@RequestMapping("/admin/course")
public class CourseController {

    private CourseService courseService;

    private UserAccountService userAccountService;

    private LectureService lectureService;

    @Autowired
    public CourseController(CourseService courseService, UserAccountService userAccountService, LectureService lectureService) {
        this.courseService = courseService;
        this.userAccountService = userAccountService;
        this.lectureService = lectureService;
    }

    /* ============================== Course ============================== */
    @GetMapping("")
    public String showCourses(Model model,
                              @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "message", required = false) String message,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<Course> listCourse = courseService.findAll(pageNo);

        if (keyword != null) {
            listCourse = courseService.searchByKeyword(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("totalPage", listCourse.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", listCourse.getSize());
        model.addAttribute("courses", listCourse);

        return "admin/course";
    }

    @GetMapping("/form")
    public String showFormUpdateCourse(@RequestParam(value = "courseId", required = false) Integer theId, Model model) {

        Course theCourse = new Course();
        if (theId != null) {
            theCourse = courseService.findById(theId);
        }
        List<UserAccount> instructorList = userAccountService.findUsersOfRole(3);

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
            List<UserAccount> instructorList = userAccountService.findUsersOfRole(3);
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

    @GetMapping("/studentOfCourse")
    public String showStudentOfCourse(Model model, @RequestParam(value = "message", required = false) String message,
                                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
//                                      @RequestParam(value = "keyword", required = false) String keyword,
                                      @RequestParam(value = "courseId") int courseId) {

        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<UserAccount> studentOfCoursePages = userAccountService.findStudentOfCourse(pageNo, courseId);
        Course course = courseService.findById(courseId);

        model.addAttribute("course", course);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", studentOfCoursePages.getSize());
        model.addAttribute("totalPage", studentOfCoursePages.getTotalPages());
        model.addAttribute("userAccounts", studentOfCoursePages);

        return "admin/list/user-list";
    }

    @GetMapping("/studentOfCourse/listAdd")
    public String listForAddStudent(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam("courseId") int courseId, @RequestParam(value = "message", required = false) String message) {

        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<UserAccount> studentPages = userAccountService.findStudentWithoutCourse(pageNo);
        Course course = courseService.findById(courseId);

        model.addAttribute("course", course);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", studentPages.getTotalPages());
        model.addAttribute("pageSize", studentPages.getSize());
        model.addAttribute("userAccounts", studentPages);

        return "admin/list/user-add-list";
    }

    @GetMapping("/studentOfCourse/listAdd/addStudentToCourse")
    public String processAddStudentToCourse(@RequestParam("courseId") int courseId, @RequestParam("userId") int userId){

        UserAccount student = userAccountService.findById(userId);
        Course course = courseService.findById(courseId);

        student.getEnrolledCourses().add(new CourseUser(course, student));

        userAccountService.save(student);

        return "redirect:/admin/course/studentOfCourse/listAdd?courseId=" + courseId + "&message=insert_success";
    }

    @GetMapping("/lectureOfCourse")
    public String showLectureOfCourse(Model model, @RequestParam(value = "message", required = false) String message,
                                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
//                                      @RequestParam(value = "keyword", required = false) String keyword,
                                      @RequestParam(value = "courseId") int courseId) {

        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<Lecture> lectureOfCoursePages = lectureService.findLectureOfCourse(pageNo, courseId);

        Course course = courseService.findById(courseId);

        model.addAttribute("course", course);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", lectureOfCoursePages.getSize());
        model.addAttribute("totalPage", lectureOfCoursePages.getTotalPages());
        model.addAttribute("lectures", lectureOfCoursePages);

        return "admin/list/lecture-list";
    }

    @GetMapping("/lectureOfCourse/listAdd")
    public String listForAddLecture(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam("courseId") int courseId, @RequestParam(value = "message", required = false) String message) {

        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<Lecture> lecturePages = lectureService.findLectureWithoutCourse(pageNo);

        Course course = courseService.findById(courseId);

        model.addAttribute("course", course);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", lecturePages.getTotalPages());
        model.addAttribute("pageSize", lecturePages.getSize());
        model.addAttribute("lectures", lecturePages);

        return "admin/list/lecture-add-list";
    }

    @GetMapping("/lectureOfCourse/listAdd/addLectureToCourse")
    public String processAddLectureToCourse(@RequestParam("courseId") int courseId, @RequestParam("lectureId") int lectureId){

        Lecture lecture = lectureService.findById(lectureId);
        Course course = courseService.findById(courseId);

        lecture.setCourseId(course);

        lectureService.save(lecture);

        return "redirect:/admin/course/lectureOfCourse/listAdd?courseId=" + courseId + "&message=insert_success";
    }


    @GetMapping("/deleteStudentOutOfCourse")
    public String deleteStudentOutOfCourse(@RequestParam("userId") int studentId, @RequestParam(value = "courseId") int courseId) {

        UserAccount theStudent = userAccountService.findById(studentId);
        theStudent.getEnrolledCourses().removeIf(instance -> instance.getCourse().getId()==courseId);
        userAccountService.save(theStudent);

        return "redirect:/admin/course/studentOfCourse?courseId=" + courseId + "&message=delete_success";
    }

    @GetMapping("/deleteLectureOutOfCourse")
    public String deleteLectureOutOfCourse(@RequestParam("lectureId") int lectureId, @RequestParam(value = "courseId") int courseId) {

        Lecture lecture = lectureService.findById(lectureId);
        lecture.setCourseId(null);
        lectureService.save(lecture);

        return "redirect:/admin/course/lectureOfCourse?courseId=" + courseId + "&message=delete_success";
    }

    @GetMapping("/studentDetail")
    public String showStudentDetail(@RequestParam("userId") int studentId, @RequestParam("courseId") int courseId, Model model) {

        UserAccount studentDetail = userAccountService.findById(studentId);

        model.addAttribute("userAccount", studentDetail);
        model.addAttribute("courseId", courseId);

        return "admin/detail/user-detail";
    }

    @GetMapping("/lectureDetail")
    public String showLectureDetail(@RequestParam("lectureId") int lectureId, @RequestParam("courseId") int courseId, Model model) {

        Lecture lecture = lectureService.findById(lectureId);

        model.addAttribute("lecture", lecture);
        model.addAttribute("courseId", courseId);

        return "admin/detail/lecture-detail";
    }



    /* ============================== End-Course ============================== */
}
