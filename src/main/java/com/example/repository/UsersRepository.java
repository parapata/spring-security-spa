package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Users;

public interface UsersRepository extends CrudRepository<Users, String> {
}
