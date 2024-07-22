package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.exception.DuplicateUsernameException;
import com.lynhatkhanh.educationweb.educationweb.model.*;
import com.lynhatkhanh.educationweb.educationweb.service.*;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CourseService courseService;
    private UserAccountService userAccountService;
    private RoleService roleService;
    private UserRoleService userRoleService;

    private LectureService lectureService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(CourseService courseService, UserAccountService userAccountService, RoleService roleService, UserRoleService userRoleService, PasswordEncoder passwordEncoder, LectureService lectureService) {
        this.courseService = courseService;
        this.userAccountService = userAccountService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.lectureService = lectureService;
    }

    @GetMapping({"/index", "/index.html", ""})
    public String showIndex(Model model) {
        CustomUserDetail user = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "admin/index";
    }

    @GetMapping("/dashboard")
    public String showDashBoard() {
        return "admin/dashboard";
    }


    /* ============================== Course ============================== */
    @GetMapping("/course")
    public String showCourses(Model model,
                              @Param("keyword") String keyword, @RequestParam(value = "message", required = false) String message,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (message != null) {
            MessageUtil.showMessage(message, model);
        }

        Page<Course> listCourse = courseService.getAll(pageNo);

        if (keyword != null) {
            listCourse = courseService.searchCourse(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("totalPage", listCourse.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("courses", listCourse);

        return "admin/course";
    }

    /*
     * TODO: merge 2 getMapping to 1 getMapping("showForm") within the same URL
     * */
    @GetMapping("/course/showFormForAdd")
    public String showFormAddCourse(Model model) {

        Course theCourse = new Course();
        List<UserAccount> instructorList = userAccountService.getUsersOfRole(3);

        model.addAttribute("course", theCourse);
        model.addAttribute("instructors", instructorList);

        return "admin/form/course-form";
    }

    @GetMapping("/course/showFormForUpdate")
    public String showFormUpdateCourse(@RequestParam("courseId") int theId, Model model) {

        // find entity
        Course theCourse = courseService.findById(theId);
        List<UserAccount> instructorList = userAccountService.getUsersOfRole(3);

        // send entity to template
        model.addAttribute("course", theCourse);
        model.addAttribute("instructors", instructorList);

        return "admin/form/course-form";
    }

    @PostMapping("/course/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course theCourse, BindingResult theBindingResult,
                             @RequestParam("instructorId-option") Integer instructorId, Model model) {
        String typeOfMessage = "";
        if (theCourse.getId() != 0)
            typeOfMessage = "update_success";
        else
            typeOfMessage = "insert_success";

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

    @GetMapping("/course/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int theId) {

        // delete entity
        courseService.deleteById(theId);

        // use a redirect to /admin/course - to prevent duplicate submissions (reload confirmation site)
        return "redirect:/admin/course";
    }

    @GetMapping("/course/showStudentOfCourse")
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


    @GetMapping("/course/deleteOutOfCourse")
    public String deleteStudentOutOfCourse(@RequestParam("userId") int studentId, @RequestParam(value = "courseId") int courseId) {

        UserAccount theStudent = userAccountService.findById(studentId);
        theStudent.getEnrolledCourses().removeIf(instance -> instance.getCourse().getId()==courseId);
        theStudent.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        userAccountService.save(theStudent);

        return "redirect:/admin/course/showStudentOfCourse?courseId=" + courseId + "&message=delete_success";
    }

    @GetMapping("/course/showStudentDetail")
    public String showStudentDetail(@RequestParam("userId") int studentId, @RequestParam("courseId") int courseId, Model model) {

        UserAccount studentDetail = userAccountService.findById(studentId);

        model.addAttribute("userAccount", studentDetail);
        model.addAttribute("courseId", courseId);

        return "admin/detail/user-detail";
    }

    /* ============================== End-Course ============================== */
    /****************************************************************************/
    /* ============================== Lecture ============================== */

    @GetMapping("/lecture")
    public String showLecture(Model model, @RequestParam(value = "message", required = false) String message,
                              @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        List<Lecture> lectures = lectureService.findAll();

        model.addAttribute("lectures", lectures);

        return "admin/lecture";
    }

    @GetMapping("/lecture/showFormUpdate")
    public String showFormUpdateLecture(Model model, @RequestParam("lectureId") int lectureId) {

        Lecture lecture = lectureService.findById(lectureId);
        List<Course> courses = courseService.findAll();

        model.addAttribute("lecture", lecture);
        model.addAttribute("courses", courses);

        return "admin/form/lecture-form";
    }

    @GetMapping("/lecture/save")
    public String saveLecture(@Valid @ModelAttribute("lecture") Lecture lecture, BindingResult bindingResult, Model model,
                              @RequestParam("courseId-option") int courseId) {

        String typeOfMessage = null;
        if (lecture.getId() != 0)
            typeOfMessage = "update_success";
        else
            typeOfMessage = "insert_success";

        if (bindingResult.hasErrors()) {
            List<Course> courses = courseService.findAll();

            model.addAttribute("lecture", lecture);
            model.addAttribute("courses", courses);
            return "admin/form/lecture-form";
        } else {
            lecture.setCourseId(courseService.findById(courseId));
            lectureService.save(lecture);
            return "redirect:/admin/lecture?message=" + typeOfMessage;
        }
    }


    /* ============================== End-Lecture ============================== */
    /****************************************************************************/
    /* ============================== User ============================== */

    @GetMapping("/user")
    public String showUsers(Model model, @RequestParam(value = "message", required = false) String message,
                            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                            @Param(value = "keyword") String keyword, @RequestParam(value = "roleName", required = false) String roleName) {

        if (message != null)
            MessageUtil.showMessage(message, model);

        int roleId = 0;
        try {
            if (roleName.equals("student"))
                roleId = 2;
            else if (roleName.equals("instructor"))
                roleId = 3;
            else if (roleName.equals("all"))
                roleId = 0;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            roleName = "all";
        }

        Page<UserAccount> studentPages = userAccountService.getUsersOfRole(pageNo, roleId);

        if (keyword != null) {
            studentPages = userAccountService.searchUsersOfRole(keyword, pageNo, roleId);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("roleName", roleName);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", studentPages.getTotalPages());
        model.addAttribute("userAccounts", studentPages);

        return "admin/user";
    }

    @GetMapping("/user/showForm")
    public String showForm(@RequestParam(value = "userId", required = false) Integer userId, Model model) {

        UserAccount userAccount = new UserAccount();

        List<String> genderOption = Arrays.asList("Male", "Female", "Another");
        model.addAttribute("genderOption", genderOption);

        List<Boolean> enableOption = Arrays.asList(true, false);
        model.addAttribute("enableOption", enableOption);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roleCheckbox", roles);

        if (userId != null) {
            userAccount = userAccountService.findById(userId);
            List<Role> userRoleRoles = userAccount.getUserRole().stream().map(userRole -> userRole.getRole()).collect(Collectors.toList());
            List<String> userRoleNames = userRoleRoles.stream().map(role -> role.getName()).collect(Collectors.toList());
            model.addAttribute("userRoleNames", userRoleNames);
        }

        model.addAttribute("userAccount", userAccount);


        return "admin/form/user-form";
    }

    @PostMapping("/user/save")
    public String saveUser(@Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult theBindingResult,
                           @RequestParam(value = "selected-role-id", required = false) List<Integer> listRoleIds, Model model) {

        List<Role> allRole = roleService.findAll();

        if (theBindingResult.hasErrors()) {
            UserAccount userAccountFromDb = userAccountService.findById(userAccount.getId());
            model.addAttribute("userAccount", userAccountFromDb);

            List<String> genderOption = Arrays.asList("Male", "Female", "Another");
            model.addAttribute("genderOption", genderOption);

            List<Boolean> enableOption = Arrays.asList(true, false);
            model.addAttribute("enableOption", enableOption);

            List<Role> roles = roleService.findAll();
            model.addAttribute("roleCheckbox", roles);

            // get List<String> of UserRole of userAccount
            List<Role> userRoleRoles = userAccountFromDb.getUserRole().stream().map(userRole -> userRole.getRole()).collect(Collectors.toList());
            List<String> userRoleNames = userRoleRoles.stream().map(role -> role.getName()).collect(Collectors.toList());
            model.addAttribute("userRoleNames", userRoleNames);

            model.addAttribute("org.springframework.validation.BindingResult.userAccount", theBindingResult);

            System.out.println("Binding results: " + theBindingResult.toString());
            return "admin/form/user-form";
        } else if (userAccount.getId() != 0) {
            UserAccount inDatabase = userAccountService.findById(userAccount.getId());

            inDatabase.setFirstName(userAccount.getFirstName());
            inDatabase.setLastName(userAccount.getLastName());
            inDatabase.setEmail(userAccount.getEmail());
            inDatabase.setTelephone(userAccount.getTelephone());
            inDatabase.setAddress(userAccount.getAddress());
            inDatabase.setGender(userAccount.getGender());
            inDatabase.setEnabled(userAccount.getEnabled());

            if (listRoleIds == null)
                inDatabase.getUserRole().clear();
            else {
                // remove unselected checkbox
                inDatabase.getUserRole().removeIf(instance -> !listRoleIds.contains(instance.getRole().getId()));

                // add selected checkbox
                for (Integer roleId : listRoleIds) {
                    if (inDatabase.getUserRole().stream().noneMatch(userRole -> userRole.getRole().getId() == roleId)) {
                        Role newRole = allRole.stream()
                                .filter(role -> role.getId() == roleId)
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + roleId));
                        inDatabase.getUserRole().add(new UserRole(newRole, inDatabase));
                    }
                }
            }
            inDatabase.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            userAccountService.save(inDatabase);
            System.out.println("saving...");
            return "redirect:/admin/user?message=update_success";
        } else {
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            userAccount.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            try {
                userAccountService.save(userAccount);
                userAccount.setUserRole(new HashSet<>());
                if (listRoleIds != null) {
                    for (Integer roleId : listRoleIds) {
                        Role newRole = allRole.stream()
                                .filter(role -> role.getId() == roleId)
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Invalid role id: " + roleId));
                        userAccount.getUserRole().add(new UserRole(newRole, userAccount));
                    }
                }
                userAccountService.save(userAccount);
                return "redirect:/admin/user?message=insert_success";
            } catch (DuplicateUsernameException e) {
                model.addAttribute("errorMessage", e.getMessage());

                List<String> genderOption = Arrays.asList("Male", "Female", "Another");
                model.addAttribute("genderOption", genderOption);

                List<Boolean> enableOption = Arrays.asList(true, false);
                model.addAttribute("enableOption", enableOption);

                List<Role> roles = roleService.findAll();
                model.addAttribute("roleCheckbox", roles);

                return "admin/form/user-form";
            }
        }
    }

    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("userId") int userId) {
        userAccountService.deleteById(userId);
        return "redirect:/admin/user?message=delete_success";
    }

    /* ============================== End-User ============================== */


}
