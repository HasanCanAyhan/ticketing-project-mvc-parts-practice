package com.cydeo.convert;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConvert implements Converter<String, UserDTO> {

    private final UserService userService;

    public UserDTOConvert(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDTO convert(String source) {
        return userService.findById(source);
    }


}
