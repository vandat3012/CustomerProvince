package com.example.customerprovince.repository;

import com.example.customerprovince.model.Customer;
import com.example.customerprovince.model.Province;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);
}
