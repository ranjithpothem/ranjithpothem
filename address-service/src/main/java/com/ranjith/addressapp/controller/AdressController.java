package com.ranjith.addressapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ranjith.addressapp.service.AddressService;
import com.ranjith.addressapp.response.AddressRespone;

@Controller
public class AdressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity <AddressRespone> getAddressByEmployeeId(@PathVariable("employeeId") int id) {
		
		AddressRespone addressRespone=null;
		addressRespone = addressService.findEmployeeByEmployeeId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(addressRespone);
		
	}

}
