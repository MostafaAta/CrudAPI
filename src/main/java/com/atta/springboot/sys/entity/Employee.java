package com.atta.springboot.sys.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "code", nullable = false)
	@NotNull(message = "Code Not Equal Null")
	private String code;
	@Column(name = "first_name", nullable = false)
	@NotNull(message = "first Name Not Equal Null")
	private String firstName;
	@Column(name = "last_name", nullable = false)
	@NotNull(message = "last Name Not Equal Null")
	private String lastName;
	@Column(name = "address", nullable = false)
	@NotNull(message = "address Not Equal Null")
	private String address;
	@Column(name = "email", nullable = false)
	@NotNull(message = "email Not Equal Null")
	private String email;

	public Employee()
	{

	}

	public Employee(String code, String firstName, String lastName, String address, String email)
	{
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		return "Employee{" + "id=" + id + ", code='" + code + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", address='" + address + '\'' + ", email='" + email + '\'' + '}';
	}

}

