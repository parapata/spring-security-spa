package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Customer;

@Repository
public class CustomerRepository implements CrudRepository<Customer, Integer> {

    private static Map<Integer, Customer> map;
    static {
        map = new HashMap<>();
        map.put(1, new Customer(1, "ichiro", "suzuki"));
    }

    @Override
    public <S extends Customer> S save(S entity) {
        if (entity.getId() == null) {
            int max = 0;
            for (Customer item : map.values()) {
                if (item.getId() > max) {
                    max = item.getId();
                }
            }
            entity.setId(++max);
            map.put(entity.getId(), entity);
        } else {
            map.put(entity.getId(), entity);
        }
        return null;
    }

    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(item -> {
            save(item);
        });
        return entities;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        Customer customer = map.get(id);
        return Optional.ofNullable(customer);
    }

    @Override
    public boolean existsById(Integer id) {
        return map.containsKey(id);
    }

    @Override
    public Iterable<Customer> findAll() {
        return new Iterable<Customer>() {
            @Override
            public Iterator<Customer> iterator() {
                return map.values().iterator();
            }
        };
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Integer> ids) {
        List<Customer> list = new ArrayList<>();
        ids.forEach(id -> {
            if (map.containsKey(id)) {
                list.add(map.get(id));
            }
        });
        return new Iterable<Customer>() {
            @Override
            public Iterator<Customer> iterator() {
                return list.iterator();
            }
        };
    }

    @Override
    public long count() {
        return map.size();
    }

    @Override
    public void deleteById(Integer id) {
        map.remove(id);
    }

    @Override
    public void delete(Customer entity) {
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {

    }

    @Override
    public void deleteAll() {
        map.clear();
    }

}
