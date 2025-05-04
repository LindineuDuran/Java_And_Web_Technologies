package br.com.lduran.config;

import br.com.lduran.entities.Employee;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;
import java.time.Period;

public class EmployeeListener
{
	@PrePersist
	public void calculateEmployeeAge(Employee employee)
	{
		employee.setAge(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears());
	}
}