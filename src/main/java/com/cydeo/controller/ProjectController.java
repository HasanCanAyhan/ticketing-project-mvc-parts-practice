package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "redirect:/project/create";
    }


    //delete button

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable String id){

        projectService.deleteById(id);

        return "redirect:/project/create";
    }

    //complete button

    @GetMapping("/complete/{id}")
    public String completeProject(@PathVariable String id){

        projectService.complete(id);

        return "redirect:/project/create";
    }

    //update button

    @GetMapping("/update/{id}")
    public String editProject(@PathVariable String id, Model model){

        model.addAttribute("project", projectService.findById(id));
        model.addAttribute("managers", userService.getManagers());
        model.addAttribute("projects",projectService.readAll());


        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") ProjectDTO project){

        projectService.update(project);

        return "redirect:/project/create";
    }






}
