package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> findAll(Pageable pageable) {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll().forEach(item -> {
            list.add(item);
        });
        return new PageImpl<>(list);
    }

    public Customer findById(Integer id) {
        Optional<Customer> res = customerRepository.findById(id);
        return res.isPresent() ? res.get() : null;
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
