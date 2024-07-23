package company.test.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer employeeDni;
    private Boolean isActive = true;

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