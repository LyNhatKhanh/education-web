package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.exception.DuplicateUsernameException;
import com.lynhatkhanh.educationweb.educationweb.model.Role;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.model.UserRole;
import com.lynhatkhanh.educationweb.educationweb.service.RoleService;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/user")
public class UserAccountController {

    private UserAccountService userAccountService;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountController(UserAccountService userAccountService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    /* ============================== User ============================== */

    @GetMapping("")
    public String showUsers(Model model, @RequestParam(value = "message", required = false) String message,
                            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(value = "keyword") String keyword, @RequestParam(value = "roleName", required = false) String roleName) {

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

        Page<UserAccount> studentPages = userAccountService.findUsersOfRole(pageNo, roleId);

        if (keyword != null) {
            studentPages = userAccountService.searchUsersOfRole(keyword, pageNo, roleId);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("roleName", roleName);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", studentPages.getTotalPages());
        model.addAttribute("pageSize", studentPages.getSize());
        model.addAttribute("userAccounts", studentPages);

        return "admin/user";
    }

    @GetMapping("/form")
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

    @PostMapping("/save")
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

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int userId) {
        userAccountService.deleteById(userId);
        return "redirect:/admin/user?message=delete_success";
    }

    /* ============================== End-User ============================== */

}
