package company.test.Service;

import company.test.Exception.DataAccessException;
import company.test.Exception.DuplicatedElement;
import company.test.Exception.ElementNotFound;
import company.test.Model.Employee;
import company.test.Repository.EmployeeRepository;
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
                    .orElseThrow(() -> new ElementNotFound("No employee found with given ID"));//service exception
        } catch (ElementNotFound e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to access DB");//repository exception (consultar)
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);//any other exception
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
        } catch (ElementNotFound e) {
            throw e;
        } catch(Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    /*save an employee*/
    public void postEmployee(Employee employee) {
        try {
            employeeRepository.findByemployeeDni(employee.getEmployeeDni())
                    .ifPresent(e -> { throw new DuplicatedElement("Employee already exists"); });
            employeeRepository.save(employee);
        }  catch (DuplicatedElement e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    /*update data of an employee by id*/
    public void updateEmployee(Long id, Employee employee) {
        try {
            Employee updatedEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("Employee not found"));
            if (!updatedEmployee.equals(employee)) {
                updatedEmployee.setEmployeeName(employee.getEmployeeName());
                updatedEmployee.setEmployeeLastName(employee.getEmployeeLastName());
                updatedEmployee.setBirthDay(employee.getBirthDay());
            }
            employeeRepository.save(updatedEmployee);
        } catch (ElementNotFound e) {
            throw e;
        } catch (Exception e){
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    /*physical delete of an employee*/
    public void deleteEmployee(Long id) {
        try {
            Employee deletedEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("No employee found with given ID"));
            if (Boolean.TRUE.equals(deletedEmployee.getIsActive())) {
                deletedEmployee.setIsActive(false);
                employeeRepository.save(deletedEmployee);
            }
        } catch (ElementNotFound e) {
            throw e;
        } catch (Exception e){
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }
}