package com.ranjith.addressapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranjith.addressapp.entity.Address;
import com.ranjith.addressapp.repo.AddressRepo;
import com.ranjith.addressapp.response.AddressRespone;


@Service
public class AddressService {
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
    private	ModelMapper modelMapper;
	
	public AddressRespone  findEmployeeByEmployeeId(int employeeId) {
		
		System.out.println("entered employeeId is "+employeeId);
	
	 Address address = addressRepo.findAddressByEmployeeId(employeeId);
	 
	 AddressRespone addressRespone = modelMapper.map(address,AddressRespone.class);
	 
	 return addressRespone;
	}

}
