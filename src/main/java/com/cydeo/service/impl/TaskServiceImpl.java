package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl extends AbstractMapServiceDB<TaskDTO,Long> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO task) {

        if (task.getId() == null){
            task.setId(UUID.randomUUID().getMostSignificantBits());
        }

        if(task.getAssignedDate() == null){
            task.setAssignedDate(LocalDate.now());
        }

        if (task.getTaskStatus() == null){
            task.setTaskStatus(Status.OPEN);
        }


        return super.save(task.getId(),task);
    }

    @Override
    public void update(TaskDTO task) {


        if (task.getAssignedDate() == null){

            task.setAssignedDate( findById(task.getId()).getAssignedDate()  );

        }


        if (task.getTaskStatus() == null){
            task.setTaskStatus(  findById(task.getId()).getTaskStatus() );
        }


        super.update(task.getId(), task);
    }

    @Override
    public List<TaskDTO> readAll() {
        return super.readAll();
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
