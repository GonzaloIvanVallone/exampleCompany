package company.test.Repository;

import company.test.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByemployeeDni(Integer dni);

    @Query("SELECT e FROM Employee e WHERE e.isActive = true")
    List<Employee> findAllEmployees();
}