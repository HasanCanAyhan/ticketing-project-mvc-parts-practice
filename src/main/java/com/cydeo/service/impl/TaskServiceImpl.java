package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl extends AbstractMapServiceDB<TaskDTO,Long> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO task) {
        return super.save(task.getId(),task);
    }

    @Override
    public void update(TaskDTO task) {
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
