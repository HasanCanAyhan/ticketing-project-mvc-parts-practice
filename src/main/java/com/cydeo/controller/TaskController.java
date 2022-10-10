package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public String saveTask(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);

        return "redirect:/task/createPage";
    }


    //delete button

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){

        taskService.deleteById(id);

        return "redirect:/task/createPage";
    }

    // update button

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable Long id,Model model){

        model.addAttribute("task", taskService.findById(id));

        model.addAttribute("projects" , projectService.readAll());

        model.addAttribute("employees", userService.findEmployees());

        model.addAttribute("tasks", taskService.readAll());


        return "/task/update";
    }


    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, TaskDTO task){

        taskService.update(task);

        return "redirect:/task/createPage";
    }


    //PENDING TASKS

    @GetMapping("/pendingTask")
    public String getPendingTaskPage(Model model){

        //model.addAttribute("tasks", taskService.readAll());
        model.addAttribute("tasks", taskService.getTasksWhichAreNotCompleted());

        return "/task/pending-tasks";

    }


    //ARCHIVE TASKS

    @GetMapping("/archiveTask")
    public String getArchiveTasksPage(Model model){

        model.addAttribute("tasks", taskService.getTasksWhichAreCompleted());

        return "task/archive";
    }

    //INSIDE PENDING TASKS - UPDATE BUTTON

    @GetMapping("/employee/edit/{id}")
    public String editEmployeeTaskStatus(@PathVariable Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks", taskService.getTasksWhichAreNotCompleted());

        return "/task/status-update";

    }

    @PostMapping("/employee/update/{id}")
    public String updateEmployeeTaskStatus(@PathVariable Long id, @ModelAttribute("task") TaskDTO task){

        taskService.updateStatus(id,task);

        return "redirect:/task/pendingTask";
    }








    //PROJECT STATUS PAGE

    @GetMapping("/status")
    public String getProjectStatusPage(Model model){

       // model.addAttribute("tasks", taskService.readAll());

        return "/task/status-update";
    }



}
