package com.library.librarydemo.service;

import com.library.librarydemo.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address findById(int theId);
    Address save(Address address);
    void deleteById(int theId);
}
