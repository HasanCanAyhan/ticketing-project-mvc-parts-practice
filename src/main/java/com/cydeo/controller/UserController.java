package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/createPage")
    public String getUserCreatePage(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.readAll());
        model.addAttribute("users",userService.readAll());
        return "/user/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") UserDTO user){

        userService.save(user);

        return "redirect:/user/createPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteById(id);
        return "redirect:/user/createPage";

    }

    @GetMapping("/update/{id}")
    public String editUser(@PathVariable String id, Model model){

        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles",roleService.readAll());
        model.addAttribute("users",userService.readAll());

        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user){

        userService.update(user);

        return "redirect:/user/createPage";
    }



}
