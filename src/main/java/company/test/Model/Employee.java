package company.test.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
//Usually replaced by @Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @Size(max = 50, message = "Employee name is too long")
    @NotNull
    private String employeeName;

    @Size(max = 50, message = "Employee lastname is too long")
    @NotNull
    private String employeeLastName;

    @NotNull
    private LocalDate birthDay;

    public Employee(String employeeName, String employeeLastName, LocalDate birthDay) {
        this.employeeName = employeeName;
        this.employeeLastName = employeeLastName;
        this.birthDay = birthDay;
    }

    public Employee() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}