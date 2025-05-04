package br.com.lduran.service;

import br.com.lduran.entities.Allowance;
import br.com.lduran.entities.Department;
import br.com.lduran.entities.Employee;
import br.com.lduran.entities.EmployeeDetails;
import br.com.lduran.entities.ParkingSpace;
import br.com.lduran.entities.Project;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Stateless
public class QueryService
{
	@Inject
	EntityManager entityManager;

	@PostConstruct
	private void init()
	{
	}

	@PreDestroy
	private void destroy()
	{

	}

	public List<Department> getAllDepartments()
	{
		return entityManager.createNamedQuery(Department.GET_DEPARTMENT_LIST, Department.class).getResultList();
	}

	public List<String> getAllDepartmentNames()
	{
		return entityManager.createNamedQuery(Department.GET_DEPARTMENT_NAMES, String.class).getResultList();
	}

	public List<ParkingSpace> getAllAllocatedParkingSpaces()
	{
		return entityManager.createNamedQuery(Employee.GET_ALL_PARKING_SPACES, ParkingSpace.class).getResultList();
	}

	public Collection<Object[]> getEmployeeProjection()
	{
		return entityManager.createNamedQuery(Employee.EMPLOYEE_PROJECTION, Object[].class).getResultList();
	}

	public List<EmployeeDetails> getEmployeeDetails()
	{
		return entityManager.createNamedQuery(Employee.EMPLOYEE_CONSTRUCTOR_PROJ, EmployeeDetails.class).getResultList();
	}

	public Collection<Allowance> getEmployeeAllowances(BigDecimal greaterThanValue)
	{
		return entityManager.createNamedQuery(Employee.GET_EMPLOYEE_ALLOWANCES, Allowance.class).setParameter("greaterThanValue", greaterThanValue).getResultList();
	}

	public Collection<Employee> filterEmployeesBySalary(BigDecimal lowerBound, BigDecimal upperBound)
	{
		return entityManager.createNamedQuery(Employee.EMPLOYEE_SALARY_BOUND, Employee.class).setParameter("upperBound", upperBound).setParameter("lowerBound", lowerBound).getResultList();
	}

	public Collection<Employee> filterEmployeesByName(String pattern)
	{
		TypedQuery<Employee> filter = entityManager.createQuery("select e from Employee e where e.fullName LIKE :filter", Employee.class).setParameter("filter", "%" + pattern + "%");//joe

		return filter.getResultList();
	}

	public Employee getEmployeeWithHighestSalary()
	{
		return entityManager.createQuery("select e from Employee e where e.basicSalary = (select max(emp.basicSalary) from Employee emp)", Employee.class).getSingleResult();
	}

	public Collection<Employee> filterEmployeesByState()
	{
		return entityManager.createQuery("select e from Employee e where e.address.state in ('NY', 'CA')", Employee.class).getResultList();
	}

	public Collection<Employee> getManagers()
	{
		return entityManager.createQuery("select e from Employee e where e.subordinates is not empty ", Employee.class).getResultList();
	}

	public Collection<Employee> getEmployeesByProject(Project project)
	{

		return entityManager.createQuery("select e from Employee e where :project member of e.projects order by e.department.departmentName desc ", Employee.class).setParameter("project", project).getResultList();

	}

	public Collection<Employee> getAllLowerPaidManagers()
	{
		return entityManager.createQuery("select e from Employee e where e.subordinates is not empty and e.basicSalary < all  (select s.basicSalary from e.subordinates s)", Employee.class).getResultList();
	}

	public Collection<Employee> filterEmployees(String pattern)
	{

		return entityManager.createQuery("select e from Employee e where e.fullName LIKE :filter", Employee.class).setParameter("filter", pattern).getResultList();//jo% jonathan, joseph, joe
	}

	//    public Collection<Employee> getEmployeesByBonus() {
	//        return entityManager.createQuery("select e, e.basicSalary * 0.15 as bonus from Employee e order by bonus ", Employee.class).getResultList();
	//    }

	public Collection<Object[]> getTotalEmployeeSalariesByDept()
	{
		TypedQuery<Object[]> query = entityManager.createQuery("select d.departmentName, sum(e.basicSalary) from Department d join d.employees e group by d.departmentName", Object[].class);
		return query.getResultList();
	}

	public Collection<Object[]> getAverageEmployeeSalaryByDept()
	{
		return entityManager.createQuery("select d.departmentName, avg(e.basicSalary) from Department d join d.employees e where e.subordinates is empty  group by d.departmentName", Object[].class).getResultList();
	}

	public Collection<Object[]> getAverageEmployeeSalaryByDept(BigDecimal minimumThreshold)
	{
		return entityManager.createQuery("select d.departmentName, avg(e.basicSalary) from Department d join d.employees e where e.subordinates is empty  group by d.departmentName having avg(e.basicSalary) > :minThreshold", Object[].class)
				.setParameter("minThreshold", minimumThreshold).getResultList();
	}

	public Collection<Object[]> countEmployeesByDept()
	{
		return entityManager.createQuery("select d.departmentName, count(e) from Department d join d.employees e group by d.departmentName", Object[].class).getResultList();
	}

	public Collection<Object[]> getEmployeesLowestByDept()
	{
		return entityManager.createQuery("select d.departmentName, max (e.basicSalary) from Department d join d.employees e group by d.departmentName", Object[].class).getResultList();
	}

	public Department findDepartmentById(Long id)
	{
		return entityManager.find(Department.class, id);
	}

	public Employee findEmployeeById(Long id)
	{
		return entityManager.find(Employee.class, id);
	}

	public List<Employee> getEmployees()
	{
		return null;
	}

	public List<Department> getDepartments()
	{
		return null;
	}

	public Collection<Employee> bla()
	{

		//select e from Employee e where e.fullName = 'Average Joe'

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
		Root<Employee> emp = c.from(Employee.class);
		CriteriaQuery<Employee> query = c.select(emp).where(cb.equal(emp.get("fullName"), "Average Joe"));

		return entityManager.createQuery(query).getResultList();
	}

	public Collection<Employee> findAllEmployeesNamedNative()
	{
		return entityManager.createNamedQuery("Employee.findAllNativeNamed", Employee.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Department> getDepartmentsNativeQuery()
	{
		return entityManager.createNativeQuery("select * from Department", Department.class).getResultList();
	}
}