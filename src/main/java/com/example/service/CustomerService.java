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

import com.example.domain.Customers;
import com.example.repository.CustomersRepository;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    public Page<Customers> findAll(Pageable pageable) {
        List<Customers> list = new ArrayList<>();
        customersRepository.findAll().forEach(item -> {
            list.add(item);
        });
        return new PageImpl<>(list);
    }

    public Customers findById(Integer id) {
        Optional<Customers> res = customersRepository.findById(id);
        return res.isPresent() ? res.get() : null;
    }

    public Customers create(Customers customer) {
        return customersRepository.save(customer);
    }

    public Customers update(Customers customer) {
        return customersRepository.save(customer);
    }

    public void delete(Integer id) {
        customersRepository.deleteById(id);
    }
}
