package com.ranjith.addressapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ranjith.addressapp.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {
	
	
	
	@Query(nativeQuery = true, value ="SELECT aa.id,aa.lane1,aa.lan2,aa.state,aa.zip from mydb.address aa join mydb.emp ee on ee.id=aa.employee_id  where aa.employee_id=:employeeId")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);

}
