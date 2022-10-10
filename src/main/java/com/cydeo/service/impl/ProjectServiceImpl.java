package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapServiceDB<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {

        if (project.getProjectStatus() == null){
            project.setProjectStatus(Status.OPEN);
        }

        return super.save(project.getProjectCode(), project);
    }

    @Override
    public void update(ProjectDTO project) {

        if (project.getProjectStatus() == null){

            project.setProjectStatus( findById(project.getProjectCode()).getProjectStatus() );

        }

        super.save(project.getProjectCode(), project);
    }

    @Override
    public List<ProjectDTO> readAll() {
        return super.readAll();
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }


    @Override
    public void complete(String id) {
        findById(id).setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> getAllProjectsFromTheManagers(UserDTO manager) {

        List<ProjectDTO> projectList = readAll().stream().filter(project -> project.getAssignedManager().equals(manager))
                .map(project -> {

                    int unfinishedTaskCounts = (int) taskService.readAll().stream().filter(task -> task.getProject().getAssignedManager().equals(manager))
                            .filter(task -> task.getProject().equals(project))
                            .filter(task -> task.getTaskStatus() != Status.COMPLETE).count();

                    int completeTaskCounts =(int) taskService.readAll().stream().filter(task -> task.getProject().getAssignedManager().equals(manager))
                            .filter(task -> task.getProject().equals(project))
                            .filter(task -> task.getTaskStatus() == Status.COMPLETE).count();

                    project.setUnfinishedTaskCounts(unfinishedTaskCounts);
                    project.setCompleteTaskCounts(completeTaskCounts);


                    return project;
                }).collect(Collectors.toList());

        return projectList;
    }
}
