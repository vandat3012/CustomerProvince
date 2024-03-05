package com.example.customerprovince.service;

import com.example.customerprovince.model.Customer;
import com.example.customerprovince.model.Province;

public interface ICustomerService extends IGenerateService<Customer>{
    Iterable<Customer> findAllByProvince(Province province);
}

