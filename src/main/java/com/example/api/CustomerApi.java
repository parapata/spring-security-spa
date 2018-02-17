package com.example.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.Customers;
import com.example.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Page<Customers> getCustomers(@PageableDefault Pageable pageable) {
        Page<Customers> customers = customerService.findAll(pageable);
        return customers;
    }

    @GetMapping(path = "{id}")
    public Customers getCustomer(@PathVariable Integer id) {
        Customers customer = customerService.findById(id);
        return customer;
    }

    @PostMapping
    public ResponseEntity<Customers> postCustomers(@RequestBody Customers customer, UriComponentsBuilder uriBuilder) {
        Customers created = customerService.create(customer);
        URI location = uriBuilder.path("api/customers/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(path = "{id}")
    public Customers putCustomer(@PathVariable Integer id, @RequestBody Customers customer) {
        customer.setId(id);
        return customerService.update(customer);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
    }
}
