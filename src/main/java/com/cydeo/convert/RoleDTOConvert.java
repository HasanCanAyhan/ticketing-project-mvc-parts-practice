package com.cydeo.convert;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class RoleDTOConvert implements Converter<String, RoleDTO> {

    private final RoleService roleService;

    public RoleDTOConvert(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {
        return roleService.findById(Long.parseLong(source));
    }
}
