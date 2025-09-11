package com.app.jobportal.repository;

import com.app.jobportal.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType,Integer> {
}
