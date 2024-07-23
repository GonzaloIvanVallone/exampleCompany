package company.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import company.test.Exception.ElementNotFound;
import company.test.Model.Company;
import company.test.Repository.CompanyRepository;
import company.test.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void testGetCompanyById() {
        Long companyId = 1L;
        Company company = new Company(companyId, "Test Company", "123 Test St", true, 123456789);
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        Company result = companyService.getCompany(companyId);
        assertNotNull(result);
        assertEquals("Test Company", result.getCompanyName());
        assertEquals("123 Test St", result.getCompanyAddress());
        assertEquals(true, result.getIsActive());
        assertEquals(123456789, result.getCompanyCuit());
    }

    @Test
    public void testGetCompanyById_NotFound() {
        Long companyId = 2L;
        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());
        assertThrows(ElementNotFound.class, () -> {
            companyService.getCompany(companyId);
        });
    }
}