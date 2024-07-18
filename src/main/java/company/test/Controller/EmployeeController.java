package company.test.Controller;

import company.test.Exception.ElementNotFound;
import company.test.Model.Employee;
import company.test.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController{
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmployees")
    public ResponseEntity<?> getAllEmployees(){
        try{
            return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
        }catch(ElementNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable("employeeId") Long id){
        try{
            return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
        }catch(ElementNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<?> postEmployee(@Valid @RequestBody Employee employee){
        try{
            employeeService.postEmployee(employee);//check if already exist?
            return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Long id,@Valid @RequestBody Employee employee){
        try{
            employeeService.updateEmployee(id, employee);
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}