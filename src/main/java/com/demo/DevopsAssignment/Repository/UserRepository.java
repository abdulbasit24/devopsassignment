package com.demo.DevopsAssignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.DevopsAssignment.Model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}

