package company.test.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

//Usually replaced by @Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    @Size(max = 50)
    @NotNull
    private String companyName;

    @Size(max = 50)
    @NotNull
    private String companyAddress;
    private Boolean isActive;
    private Integer companyCuit;

    public Company(String companyName, String companyAddress, Integer companyCuit) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyCuit = companyCuit;
        this.isActive = true;
    }

    public Company() {
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyId, company.companyId) && Objects.equals(companyName, company.companyName) && Objects.equals(companyAddress, company.companyAddress) && Objects.equals(isActive, company.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyName, companyAddress, isActive);
    }

    public Integer getCompanyCuit() {
        return companyCuit;
    }

    public void setCompanyCuit(Integer companyCuit) {
        this.companyCuit = companyCuit;
    }
}