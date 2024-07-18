package company.test;


import company.test.Model.Employee;
import company.test.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class AppTest {


	public static void main(String[] args) {
		Employee empleado = new Employee("Gonzalo","Vallone", LocalDate.parse("1995-12-21"));
		EmployeeService employeeService1 = new EmployeeService();
		employeeService1.postEmployee(empleado);
		System.out.println(employeeService1.getAllEmployees());
	}
}