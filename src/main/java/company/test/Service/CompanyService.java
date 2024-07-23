package company.test.Service;

import company.test.Exception.DuplicatedElement;
import company.test.Exception.ElementNotFound;
import company.test.Model.Company;
import company.test.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    /*get 1 company by id*/
    public Company getCompany(Long id) {
        try {
            return companyRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("No company found with given ID"));
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    /*get all companies*/
    public List<Company> getCompanies() {
        try {
            List<Company> optList = companyRepository.findAllCompanies();
            if (optList.isEmpty()) {
                throw new ElementNotFound("No companies found");
            }
            return optList;
        }  catch (ElementNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    /*save a company*/
    public void postCompany(Company company) {
        try {
            companyRepository.findBycompanyCuit(company.getCompanyCuit())
                    .ifPresent(e -> { throw new DuplicatedElement("Company already exists"); });
            companyRepository.save(company);
        } catch (DuplicatedElement e) {
            throw e;
        }  catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    /*update data of a company by id*/
    public void updateCompany(Long id, Company company) {
        try {
            Company updatedCompany = companyRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("Company not found"));
            if (!updatedCompany.equals(company)) {
                updatedCompany.setCompanyName(company.getCompanyName());
                updatedCompany.setCompanyAddress(company.getCompanyAddress());
                updatedCompany.setCompanyCuit(company.getCompanyCuit());
                companyRepository.save(updatedCompany);
            }
        } catch (ElementNotFound e) {
            throw e;
        }  catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    /*logical delete of a company*/
    public void deleteCompany(Long id) {
        try {
            Company deletedCompany = companyRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("No company found with given ID"));
            if(Boolean.TRUE.equals(deletedCompany.getIsActive())){
                deletedCompany.setIsActive(false);
                companyRepository.save(deletedCompany);
            }
        } catch (ElementNotFound e) {
            throw e;
        }  catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }
}