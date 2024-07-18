package company.test.Service;

import company.test.Exception.ElementNotFound;
import company.test.Model.Employee;
import company.test.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    /*get 1 employee by id*/
    public Employee getEmployee(Long id){
        Optional<Employee> opt = employeeRepository.findById(id);
        return opt.orElseThrow(() -> new ElementNotFound("No Employee found with given ID"));
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
            employeeRepository.save(employee);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
    /*update data of an employee by id*/
    public void updateEmployee(Long id,Employee employee){
        Optional<Employee> opt = employeeRepository.findById(id);
        if(opt.isEmpty()){
            throw new ElementNotFound("No Employee found with given ID");
        }
        //doubt Employee empleado = new Employee();
        opt.get().setEmployeeName(employee.getEmployeeName());
        opt.get().setEmployeeLastName(employee.getEmployeeLastName());
        opt.get().setBirthDay(employee.getBirthDay());
        employeeRepository.save(opt.get());
    }

    /*physical delete of an employee*/
    public void deleteEmployee(Long id){
        Optional<Employee> opt = employeeRepository.findById(id);
        if(opt.isEmpty()){
            throw new ElementNotFound("No Employee found with given ID");
        }
        employeeRepository.delete(opt.get());
    }
}