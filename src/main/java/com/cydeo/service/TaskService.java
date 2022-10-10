package com.cydeo.service;

import com.cydeo.dto.TaskDTO;

import java.util.List;

public interface TaskService extends CRUDService<TaskDTO,Long>{

    List<TaskDTO> getTasksWhichAreNotCompleted();
    List<TaskDTO> getTasksWhichAreCompleted();

    void updateStatus(Long id,TaskDTO task);

}
