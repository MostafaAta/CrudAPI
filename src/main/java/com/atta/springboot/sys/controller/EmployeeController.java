package com.atta.springboot.sys.controller;

import com.atta.springboot.sys.dto.EmployeeDTO;
import com.atta.springboot.sys.exception.ConflictException;
import com.atta.springboot.sys.exception.CustomValidationException;
import com.atta.springboot.sys.exception.NotFoundException;
import com.atta.springboot.sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
	@Autowired
	Validator validator;
	@Autowired
	EmployeeService employeeService;

	@GetMapping()
	public List<EmployeeDTO> getAllEmployees()
	{
		return employeeService.findAll();
	}

	@GetMapping("/id/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable long id) throws NotFoundException
	{
		try
		{
			return employeeService.findById(id);
		}
		catch (NoSuchElementException ex)
		{
			throw new NotFoundException(String.format("Record Not Found With Id  [" + id + "]"));
		}

	}

	//  get by Code - /employees/code/asd
	// get Emp By Code
	@GetMapping("/code/{code}")
	public EmployeeDTO getEmployeeByCode(@PathVariable String code) throws NotFoundException
	{
		try
		{
			return employeeService.findByCode(code);
		}
		catch (NoSuchElementException ex)
		{
			throw new NotFoundException(String.format("Record Not Found With Code  [" + code + "]"));
		}

	}

	@PostMapping("/addEmployee")
	public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO EmployeeDTO, Errors errors) throws Exception
	{
		if (errors.hasErrors())
		{
			throw new CustomValidationException(errors);
		}
		if (employeeService.findByCode(EmployeeDTO.getCode()) != null)
		{
			throw new ConflictException("This Employee Code is exists , choose another one!");
		}
		try
		{
			EmployeeDTO employee = employeeService.save(EmployeeDTO);
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		}
		catch (ConstraintViolationException ex)
		{
			throw new ConstraintViolationException(ex.getConstraintViolations());
		}
		catch (Exception ex)
		{
			throw new Exception(ex.getMessage());
		}
	}

	@PutMapping("/{id}")
	public EmployeeDTO updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO, Errors errors) throws Exception
	{
		if (errors.hasErrors())
		{
			throw new CustomValidationException(errors);
		}
		//		if (employeeService.findByCode(employeeDTO.getCode()) != null)
		//		{
		//			throw new ConflictException("This Employee Code is exists , choose another one!");
		//		}
		try
		{
			employeeService.findById(id);
			employeeDTO.setId(id);
			EmployeeDTO updatedEmp = employeeService.update(employeeDTO);
			return updatedEmp;
		}
		catch (NoSuchElementException ex)
		{
			throw new NotFoundException(ex.getMessage());
		}
		catch (ConstraintViolationException ex)
		{
			throw new ConstraintViolationException(ex.getConstraintViolations());
		}
		catch (Exception ex)
		{
			throw new Exception(ex.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	public boolean deleteEmployee(@PathVariable long id) throws Exception
	{
		boolean status = false;
		try
		{
			status = this.employeeService.deleteById(id);
		}
		catch (NoSuchElementException ex)
		{
			throw new NotFoundException(String.format("Record Not Found With Id  [" + id + "]"));
		}
		catch (Exception ex)
		{
			throw new Exception(String.format("Error in Deleting this Record , " + ex.getLocalizedMessage()));
		}
		return status;
	}
}