package com.ranjith.openfeignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ranjith.response.AddressRespone;

@FeignClient(name = "ADDRESS-SERVICE", path="/address-app/api/")
public interface AddressClient {
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity <AddressRespone> getAddressByEmployeeId(@PathVariable("employeeId") int id) ;
	
	

}
