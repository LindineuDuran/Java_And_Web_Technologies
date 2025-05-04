package br.com.lduran.service;

import br.com.lduran.config.JpaConfig;
import br.com.lduran.entities.Department;
import br.com.lduran.entities.Employee;
import br.com.lduran.entities.ParkingSpace;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class PersistenceService
{
	EntityManager entityManager = JpaConfig.getEntityManager();

	@Inject
	QueryService queryService;

	public void saveDepartment(Department department)
	{
		entityManager.persist(department);
	}

	public void removeParkingSpace(Long employeeId)
	{
		Employee employee = queryService.findEmployeeById(employeeId);
		ParkingSpace parkingSpace = employee.getParkingSpace();

		employee.setParkingSpace(null);

		entityManager.remove(parkingSpace);

	}

	public void saveEmployee(Employee employee, ParkingSpace parkingSpace)
	{

		employee.setParkingSpace(parkingSpace);
		entityManager.persist(employee);

	}

	public void saveEmployee(Employee employee)
	{
		entityManager.persist(employee);
	}

	public void updateDepartment(Department department)
	{
		entityManager.merge(department);
	}
}