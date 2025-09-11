package com.app.jobportal.service;

import com.app.jobportal.entity.User;
import com.app.jobportal.entity.UserType;
import com.app.jobportal.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {

    private UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository)
    {
        this.userTypeRepository=userTypeRepository;
    }

    public List<UserType> getAllTypes()
    {
        return userTypeRepository.findAll();
    }

}
