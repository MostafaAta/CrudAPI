package com.atta.springboot.sys.service;

import com.atta.springboot.sys.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService
{

	public List<EmployeeDTO> findAll();

	public EmployeeDTO findById(Long theId);

	public EmployeeDTO save(EmployeeDTO employeeDto);

	public EmployeeDTO update(EmployeeDTO employeeDto);

	public boolean deleteById(Long id);

	EmployeeDTO findByCode(String code);

}
