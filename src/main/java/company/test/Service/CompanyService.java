package company.test.Service;

import company.test.Exception.ElementNotFound;
import company.test.Model.Company;
import company.test.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService{
    @Autowired
    CompanyRepository companyRepository;

    /*get 1 company by id*/
    public Company getCompany(Long id){
        Optional<Company> opt = companyRepository.findById(id);
        return opt.orElseThrow(() -> new ElementNotFound("No company found with given ID"));
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
            companyRepository.save(company);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
    /*update data of a company by id*/
    public void updateCompany(Long id, Company company){
        try{
            Optional<Company> opt = companyRepository.findById(id);
            if(opt.isEmpty()){
                throw new ElementNotFound("Something went wrong");
            }
            opt.get().setCompanyName(company.getCompanyName());
            opt.get().setCompanyAddress(company.getCompanyAddress());
            companyRepository.save(opt.get());
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    /*physical or logical delete of a company*/
    public void deleteCompany(Long id){
        try{
            Optional<Company> opt = companyRepository.findById(id);
            if(opt.isEmpty()){
                throw new ElementNotFound("No Company found with given ID");
            }
            opt.get().setActive(false);
            companyRepository.save(opt.get());//logical
            companyRepository.delete(opt.get());//physical
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}