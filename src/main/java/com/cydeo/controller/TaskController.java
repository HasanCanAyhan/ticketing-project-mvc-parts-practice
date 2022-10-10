package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;

    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }


    @GetMapping("/createPage")
    public String getTaskCreatePage(Model model){


        model.addAttribute("task", new TaskDTO());

        model.addAttribute("projects" , projectService.readAll());

        model.addAttribute("employees", userService.findEmployees());

        model.addAttribute("tasks", taskService.readAll());

        return "task/create";
    }






}
