package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractMapServiceDB<UserDTO,String> implements UserService {


    @Override
    public UserDTO save(UserDTO user) {
        return super.save(user.getUserName(), user);
    }

    @Override
    public void update(UserDTO user) {
        super.save(user.getUserName(), user);
    }

    @Override
    public List<UserDTO> readAll() {
        return super.readAll();
    }

    @Override
    public UserDTO findById(String username) {
        return super.findById(username);
    }

    @Override
    public void deleteById(String username) {
        super.deleteById(username);
    }
}