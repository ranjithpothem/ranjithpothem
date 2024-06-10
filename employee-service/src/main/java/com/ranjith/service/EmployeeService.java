package com.ranjith.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ranjith.entity.Employee;
import com.ranjith.openfeignclient.AddressClient;
import com.ranjith.repo.EmployeeRepo;
import com.ranjith.response.AddressRespone;
import com.ranjith.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;//we need to create been in config class
	
	@Autowired
	private RestTemplate restTemplate;//using restTemplate for communicate the microservices and we need to create bean in config class

	
	@Value("${addressservice.base.url}")//this is coming from application.properties
	private String addressBaseURL;
	//this commented for weebClient we want use webflux(webclient ) use can give in the config class at the time of bean creation.
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;// this is for the get instances from the discovery server
	
	@Autowired
	private LoadBalancerClient loadBalancerClient; //this is for the loadbalancing
	
	@Autowired
	private AddressClient addressClient;
	
	
	public EmployeeResponse getEmployeeById(int id) {

		Employee employee = employeeRepo.findById(id).get();
		
		//employee ----> employeeResponse 1st way
		
//		EmployeeResponse employeeResponse= new EmployeeResponse();
//		employeeResponse.setId(employee.getId());
//		employeeResponse.setBoold(employee.getBoold());
//		employeeResponse.setEmail(employee.getEmail());
//		employeeResponse.setName(employee.getName());
		
		
		//using model mapper 2nd  way
		EmployeeResponse employeeResponse = modelMapper.map(employee,EmployeeResponse.class);
		
		//to communicate other microservices
		AddressRespone addressRespone= new AddressRespone();
		//one way to pass the full url
		//addressRespone=restTemplate.getForObject("http://localhost:8080/address-app/api/address/{id}", AddressRespone.class,id);
		//second way to pass the url
		//addressRespone=callindAddressServiceUsingRestTemplate(id);
		
		//using restTemplate
//		addressRespone=callindAddressServiceUsingRestTemplate(id);
//		employeeResponse.setAddressResponse(addressRespone);
//		
		//using web client
		//addressRespone=callToAddressServiceUsingWebClient(id);
		
		
		
		//using feign client
		addressRespone=addressClient.getAddressByEmployeeId(id).getBody();
		employeeResponse.setAddressResponse(addressRespone);
		
		return employeeResponse;
	}
	
	private AddressRespone callToAddressServiceUsingWebClient(int id) {
		return webClient.get()
				.uri("/address/"+id)
				.retrieve()
				.bodyToMono(AddressRespone.class)
				.block();	
	}

	private AddressRespone callindAddressServiceUsingRestTemplate(int id) {
		
		List<ServiceInstance> instances = discoveryClient.getInstances("address-service");
		ServiceInstance serviceInstance = instances.get(0);//take only one
		String uri = serviceInstance.getUri().toString();
		
		//System.out.println("uri----------->"+uri);
		
		//another way to get the uri using loadbalancerclient;
		
		ServiceInstance choose = loadBalancerClient.choose("address-service");
		String uriUsingLoadBalancerClient = choose.getUri().toString();
		String contextPath = choose.getMetadata().get("configPath");
		
		
		
		System.out.println("uri----------->"+uriUsingLoadBalancerClient+contextPath);
		
		
		//return restTemplate.getForObject(uriUsingLoadBalancerClient+contextPath+"/address/{id}", AddressRespone.class,id);
		
		return restTemplate.getForObject("http://address-service"+contextPath+"/address/{id}", AddressRespone.class,id);
	}

}
