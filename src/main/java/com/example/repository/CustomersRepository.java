package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Customers;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {
}
