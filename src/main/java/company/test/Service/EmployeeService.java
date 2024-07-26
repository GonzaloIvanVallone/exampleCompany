package company.test.Service;

import company.test.Exception.DataAccessException;
import company.test.Exception.DuplicatedElement;
import company.test.Exception.ElementNotFound;
import company.test.Exception.UnexpectedError;
import company.test.Model.Employee;
import company.test.Repository.EmployeeRepository;
import company.test.Utils.NullRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    /*get 1 employee by id*/
    public Employee getEmployee(Long id) {
        try {
            return employeeRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("No employee found with given ID"));
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to access DB");
        }
    }

    /*get all employees*/
    public List<Employee> getAllEmployees() {
        try {
            List<Employee> optList = employeeRepository.findAllEmployees();
            if (optList.isEmpty()) {
                throw new ElementNotFound("No employees found");
            }
            return optList;
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to access DB");
        }
    }

    /*save an employee*/
    public void postEmployee(Employee employee) {
        try {
            employeeRepository.findByemployeeDni(employee.getEmployeeDni())
                    .ifPresent(e -> { throw new DuplicatedElement("Employee already exists"); });
            employeeRepository.save(employee);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to access DB");
        }
    }

    /*update data of an employee by id*/
    public void updateEmployee(Long id, Employee employee) {
        try {
            Employee updatedEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("Employee not found"));
            NullRemover.copyNonNullProperties(employee, updatedEmployee);
            employeeRepository.save(updatedEmployee);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to access DB");
        }
    }

    /*logical delete of an employee*/
    public void deleteEmployee(Long id) {
        try {
            Employee deletedEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("No employee found with given ID"));
            if (Boolean.TRUE.equals(deletedEmployee.getIsActive())) {
                deletedEmployee.setIsActive(false);
                employeeRepository.save(deletedEmployee);
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to access DB");
        }
    }
}