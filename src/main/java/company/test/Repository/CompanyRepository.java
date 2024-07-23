package company.test.Repository;

import company.test.Model.Company;
import company.test.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findBycompanyCuit(Integer cuit);
    @Query("SELECT e FROM Company e WHERE e.isActive = true")
    List<Company> findAllCompanies();
}