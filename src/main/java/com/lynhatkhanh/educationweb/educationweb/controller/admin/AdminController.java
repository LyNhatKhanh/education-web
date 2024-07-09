package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.model.*;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import com.lynhatkhanh.educationweb.educationweb.service.RoleService;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
import com.lynhatkhanh.educationweb.educationweb.service.UserRoleService;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    public AdminController(CourseService courseService, UserAccountService userAccountService, RoleService roleService, UserRoleService userRoleService) {
        this.courseService = courseService;
        this.userAccountService = userAccountService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
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
    public String showCourses(Model theModel,
                              @Param("keyword") String keyword,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
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

    /*
     * TODO: merge 2 getMapping to 1 getMapping("showForm") within the same URL
     * */
    @GetMapping("/course/showFormForAdd")
    public String showFormAddCourse(Model theModel) {

        Course theCourse = new Course();
//        List<Instructor> listInstructor = instructorService.findAll();

        theModel.addAttribute("course", theCourse);
//        theModel.addAttribute("instructors", listInstructor);

        return "admin/form/course-form";
    }

    @GetMapping("/course/showFormForUpdate")
    public String showFormUpdateCourse(@RequestParam("courseId") int theId, Model theModel) {

//        List<Instructor> listInstructor = instructorService.findAll();

        // find entity
        Course theCourse = courseService.findById(theId);

        // send entity to template
        theModel.addAttribute("course", theCourse);
//        theModel.addAttribute("instructors", listInstructor);

        return "admin/form/course-form";
    }

    @PostMapping("/course/save")
    public String saveCourse(@ModelAttribute("course") Course theCourse) {

        // save entity
        courseService.save(theCourse);

        // use a redirect to /admin/course - to prevent duplicate submissions (reload confirmation site)
        return "redirect:/admin/course";
    }

    @GetMapping("/course/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int theId) {

        // delete entity
        courseService.deleteById(theId);

        // use a redirect to /admin/course - to prevent duplicate submissions (reload confirmation site)
        return "redirect:/admin/course";
    }



    /* ============================== End-Course ============================== */


    /* ============================== User ============================== */
    @GetMapping("/user")
    public String showUsers(Model model, @RequestParam(value = "message", required = false) String message,
                            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                            @Param(value = "keuword") String keyword) {
        if (message != null)
            MessageUtil.showMessage(message, model);

        Page<UserAccount> listUsers = userAccountService.getAll(pageNo);

        if (keyword != null) {
            listUsers = userAccountService.searchUser(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", listUsers.getTotalPages());
        model.addAttribute("userAccounts", listUsers);

        return "admin/user";
    }

    @GetMapping("/user/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int userId, Model model) {

        UserAccount userAccount = userAccountService.findById(userId);
        model.addAttribute("userAccount", userAccount);

        List<String> genderOption = Arrays.asList("Male", "Female", "Another");
        model.addAttribute("genderOption", genderOption);

        List<Boolean> enableOption = Arrays.asList(true, false);
        model.addAttribute("enableOption", enableOption);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roleCheckbox", roles);

        // get List<String> of UserRole of userAccount
        List<Role> userRoleRoles = userAccount.getUserRole().stream().map(userRole -> userRole.getRole()).collect(Collectors.toList());
        List<String> userRoleNames = userRoleRoles.stream().map(role -> role.getName()).collect(Collectors.toList());
        model.addAttribute("userRoleNames", userRoleNames);

        return "admin/form/user-form";
    }

    @PostMapping("user/update")
    public String saveUser(@Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult theBindingResult,
                           @RequestParam(value = "selected-role-id", required = false) List<Integer> listRoleIds, Model model) {


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
        } else {
            UserAccount inDatabase = userAccountService.findById(userAccount.getId());

            inDatabase.setFirstName(userAccount.getFirstName());
            inDatabase.setLastName(userAccount.getLastName());
            inDatabase.setEmail(userAccount.getEmail());
            inDatabase.setTelephone(userAccount.getTelephone());
            inDatabase.setAddress(userAccount.getAddress());
            inDatabase.setGender(userAccount.getGender());
            inDatabase.setEnabled(userAccount.getEnabled());

            List<Role> allRole = roleService.findAll();
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
        }
    }


    /* ============================== End-User ============================== */


}
