package com.example.springbootdemowebservices.repository;

import com.example.springbootdemowebservices.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
