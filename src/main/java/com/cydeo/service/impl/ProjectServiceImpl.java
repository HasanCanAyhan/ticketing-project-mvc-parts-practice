package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapServiceDB<ProjectDTO,String> implements ProjectService {

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
}
