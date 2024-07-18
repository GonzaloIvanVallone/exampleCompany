package company.test.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

//Usually replaced by @Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    @Size(max = 50, message = "Company name is too long")
    @NotNull(message = "Company name cannot be null")
    private String companyName;

    @Size(max = 50, message = "Company address name is too long")
    @NotNull(message = "Company address cannot be null")
    private String companyAddress;
    private Boolean isActive;

    public Company(String companyName, String companyAddress) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
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
}