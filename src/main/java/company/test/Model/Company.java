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
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;
    @Size(max = 50)
    private String companyName;
    @Size(max = 50)
    private String companyAddress;
    private Boolean isActive = true;
    private Integer companyCuit;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyId, company.companyId) && Objects.equals(companyName, company.companyName) && Objects.equals(companyAddress, company.companyAddress) && Objects.equals(isActive, company.isActive) && Objects.equals(companyCuit, company.companyCuit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyName, companyAddress, isActive, companyCuit);
    }
}