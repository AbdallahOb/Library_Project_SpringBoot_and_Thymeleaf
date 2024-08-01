package com.library.librarydemo.service;

import com.library.librarydemo.dao.AddressRepository;
import com.library.librarydemo.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository=addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(int theId) {
        Optional<Address> result= addressRepository.findById(theId);
        Address address = null;
        if(result.isPresent()){
            address = result.get();
        }else {
            throw new RuntimeException("Did not find address id - "+theId);
        }
        return address;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(int theId) {
        addressRepository.deleteById(theId);
    }
}
