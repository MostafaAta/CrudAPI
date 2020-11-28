package com.atta.springboot.sys.service;

import com.atta.springboot.sys.dao.EmployeeRepository;
import com.atta.springboot.sys.dto.EmployeeDTO;
import com.atta.springboot.sys.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDto) throws ConstraintViolationException
	{
		try
		{
			Employee employee = convertToEntity(employeeDto);
			employeeRepo.save(employee);
			employeeDto.setId(employee.getId());
			return employeeDto;
		}
		catch (ConstraintViolationException ex)
		{
			throw new ConstraintViolationException(ex.getConstraintViolations());
		}
	}

	@Override
	public EmployeeDTO update(EmployeeDTO employeeDto)
	{
		Employee employee = convertToEntity(employeeDto);
		Optional<Employee> newEmp = this.employeeRepo.findById(employee.getId());
		if (newEmp.isPresent())
		{
			Employee updatedEmp = newEmp.get();
			updatedEmp.setId(employee.getId());
			updatedEmp.setCode(employee.getCode());
			updatedEmp.setFirstName(employee.getFirstName());
			updatedEmp.setLastName(employee.getLastName());
			updatedEmp.setAddress(employee.getAddress());
			updatedEmp.setEmail(employee.getEmail());
			employeeRepo.save(updatedEmp);
			return employeeDto;
		}
		else
		{
			return employeeDto;
		}
	}

	@Override
	public List<EmployeeDTO> findAll()
	{
		List<Employee> allEmpolyees = employeeRepo.findAll();
		List<EmployeeDTO> employees = new ArrayList<>();
		for (Employee employee : allEmpolyees)
		{
			EmployeeDTO employeeDTO = convertToDto(employee);
			employees.add(employeeDTO);
		}
		return employees;
	}

	@Override
	public EmployeeDTO findById(Long empId)
	{
		if (employeeRepo.findById(empId).get() == null)
		{
			throw new NoSuchElementException();
		}
		return convertToDto(employeeRepo.findById(empId).get());
	}

	@Override
	public boolean deleteById(Long id)
	{
		Employee user = employeeRepo.findById(id).get();
		if (user != null)
		{
			employeeRepo.delete(this.employeeRepo.findById(id).get());
			return true;
		}
		else
		{
			throw new NoSuchElementException();
		}

	}

	@Override
	public EmployeeDTO findByCode(String code)
	{
		Employee user = employeeRepo.findByCode(code);
		return (user == null) ? null : convertToDto(user);
	}

	public EmployeeDTO convertToDto(Employee user)
	{
		EmployeeDTO EmployeeDTO = modelMapper.map(user, EmployeeDTO.class);
		return EmployeeDTO;
	}

	public Employee convertToEntity(EmployeeDTO EmployeeDTO)
	{
		Employee user = modelMapper.map(EmployeeDTO, Employee.class);
		return user;
	}

}

