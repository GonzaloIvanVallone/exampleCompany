package company.test.Service;

import company.test.Exception.DuplicatedElement;
import company.test.Exception.ElementNotFound;
import company.test.Model.Employee;
import company.test.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    /*get 1 employee by id*/
    public Employee getEmployee(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("No employee found with given ID"));
    }

    /*get all employees*/
    public List<Employee> getAllEmployees(){
        List<Employee> optList = employeeRepository.findAll();
        if(optList.isEmpty()){
            throw new ElementNotFound("No employees found");
        }
        return optList;
    }

    /*save an employee*/
    public void postEmployee(Employee employee){
        try{
            /*employeeRepository.findById(employee.getEmployeeId())
                    .ifPresent(e -> { throw new DuplicatedElement("Employee already exists"); });
                    */
            employeeRepository.save(employee);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    /*update data of an employee by id*/
    public void updateEmployee(Long id,Employee employee){
        Employee updatedEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("Employee not found"));
        updatedEmployee.setEmployeeName(employee.getEmployeeName());
        updatedEmployee.setEmployeeLastName(employee.getEmployeeLastName());
        updatedEmployee.setBirthDay(employee.getBirthDay());
        employeeRepository.save(updatedEmployee);
    }

    /*physical delete of an employee*/
    public void deleteEmployee(Long id){
        Employee deletedEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("No employee found with given ID"));
        employeeRepository.delete(deletedEmployee);
    }
}