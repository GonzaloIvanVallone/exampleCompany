package company.test.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

//Usually replaced by @Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @Size(max = 50)
    @NotNull
    private String employeeName;

    @Size(max = 50)
    @NotNull
    private String employeeLastName;

    @NotNull
    private LocalDate birthDay;
    @NotNull
    private Integer employeeDni;
    private Boolean isActive;

    public Employee(String employeeName, String employeeLastName, LocalDate birthDay, Integer employeeDni) {
        this.employeeName = employeeName;
        this.employeeLastName = employeeLastName;
        this.birthDay = birthDay;
        this.employeeDni = employeeDni;
        this.isActive = true;
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

    public Integer getEmployeeDni() {
        return employeeDni;
    }

    public void setEmployeeDni(Integer employeeDni) {
        this.employeeDni = employeeDni;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(employeeName, employee.employeeName) && Objects.equals(employeeLastName, employee.employeeLastName) && Objects.equals(birthDay, employee.birthDay) && Objects.equals(employeeDni, employee.employeeDni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeName, employeeLastName, birthDay, employeeDni);
    }
}