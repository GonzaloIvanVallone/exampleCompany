package company.test.Controller;

import company.test.Exception.DuplicatedElement;
import company.test.Exception.ElementNotFound;
import company.test.Model.Company;
import company.test.Service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/getCompanies")
    public ResponseEntity<?> getAllCompanies() {
        try {
            return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
        } catch (ElementNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCompany/{companyId}")
    public ResponseEntity<?> getCompany(@PathVariable("companyId") Long id) {
        try {
            return new ResponseEntity<>(companyService.getCompany(id), HttpStatus.OK);
        } catch (ElementNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createCompany")
    public ResponseEntity<?> postCompany(@Valid @RequestBody Company company) {
        try {
            companyService.postCompany(company);
            return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
        } catch (DuplicatedElement e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCompany/{companyId}")
    public ResponseEntity<?> updateCompany(@PathVariable("companyId") Long id, @Valid @RequestBody Company company) {
        try {
            companyService.updateCompany(id, company);
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        } catch (ElementNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCompany/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable("companyId") Long id) {
        try {
            companyService.deleteCompany(id);
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        } catch (ElementNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to delete company, please retry later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}