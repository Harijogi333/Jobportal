package com.app.jobportal.service;

import com.app.jobportal.entity.JobSeekerProfile;
import com.app.jobportal.entity.RecruiterProfile;
import com.app.jobportal.entity.User;
import com.app.jobportal.repository.JobSeekerProfileRepository;
import com.app.jobportal.repository.RecruiterProfileRepository;
import com.app.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private JobSeekerProfileRepository jobSeekerProfileRepository;
    private RecruiterProfileRepository recruiterProfileRepository;

    public UserService(UserRepository userRepository,JobSeekerProfileRepository jobSeekerProfileRepository,RecruiterProfileRepository recruiterProfileRepository)
    {
        this.userRepository=userRepository;
        this.jobSeekerProfileRepository=jobSeekerProfileRepository;
        this.recruiterProfileRepository=recruiterProfileRepository;
    }

    public User addNewUser(User user)
    {
        user.setActive(true);
        int userTypeId=user.getUserTypeId().getUserTypeId();
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        User savedUser=userRepository.save(user);
        if(userTypeId==1)
        {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }
        else
        {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public Optional<User> getUserBYEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}
