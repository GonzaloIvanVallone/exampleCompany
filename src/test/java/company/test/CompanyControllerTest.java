package company.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import company.test.Controller.CompanyController;
import company.test.Model.Company;
import company.test.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {//creation of simulated objects

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    public void testGetCompanyById() throws Exception {
        Company company = new Company(3L, "Test Company", "123 Test St", true, 123456789);
        when(companyService.getCompany(3L)).thenReturn(company);//saved in the mock internal state

        mockMvc.perform(get("/api/v1/getCompany/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").value(3))
                .andExpect(jsonPath("$.companyName").value("Test Company"))
                .andExpect(jsonPath("$.companyAddress").value("123 Test St"))
                .andExpect(jsonPath("$.isActive").value(true))
                .andExpect(jsonPath("$.companyCuit").value(123456789));
    }
}
