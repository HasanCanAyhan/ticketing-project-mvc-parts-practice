package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getProjectCreatePage(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.getManagers());
        model.addAttribute("projects",projectService.readAll());

        return "/project/create";

    }


    @PostMapping("/create")
    public String saveProject(@ModelAttribute("project") ProjectDTO project){

        projectService.save(project);

        return "redirect:/create";
    }


}
