package com.atta.springboot.sys.dao;

import java.util.List;

import com.atta.springboot.sys.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.atta.springboot.sys.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

	// that's it ... no need to write any code LOL!

	// add a method to sort by last name
	public List<EmployeeDTO> findAllByOrderByCodeAsc();

	Employee findByCode(String code);

}
