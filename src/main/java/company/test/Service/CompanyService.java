package company.test.Service;

import company.test.Exception.DuplicatedElement;
import company.test.Exception.ElementNotFound;
import company.test.Model.Company;
import company.test.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyService{
    @Autowired
    CompanyRepository companyRepository;

    /*get 1 company by id*/
    public Company getCompany(Long id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("No company found with given ID"));
    }

    /*get all companies*/
    public List<Company> getCompanies(){
        List<Company> optList = companyRepository.findAll();
        if(optList.isEmpty()){
            throw new ElementNotFound("No companies found");
        }
        return optList;
    }

    /*save a company*/
    public void postCompany(Company company){
        try{
            companyRepository.findById(company.getCompanyId())//need another unique value here
                    .ifPresent(e -> { throw new DuplicatedElement("Company already exists"); });
            companyRepository.save(company);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    /*update data of a company by id*/
    public void updateCompany(Long id, Company company){
        try{
            Company updatedCompany = companyRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("Company not found"));
            updatedCompany.setCompanyName(company.getCompanyName());
            updatedCompany.setCompanyAddress(company.getCompanyAddress());
            companyRepository.save(updatedCompany);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    /*physical or logical delete of a company*/
    public void deleteCompany(Long id){
        try{
            Company deletedCompany = companyRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFound("No company found with given ID"));
            deletedCompany.setActive(false);
            companyRepository.save(deletedCompany);//logical
            companyRepository.delete(deletedCompany);//physical
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}