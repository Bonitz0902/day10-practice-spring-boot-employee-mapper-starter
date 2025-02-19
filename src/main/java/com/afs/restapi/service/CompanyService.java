package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.CompanyNotFoundException;
import com.afs.restapi.mapper.CompanyMapper;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.request.CompanyRequest;
import com.afs.restapi.response.CompanyResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id) { //TODO: Utilize CompanyResponse
        return companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
    }

    public List<Company> findByPage(Integer pageNumber, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageNumber-1, pageSize)).stream()
                .collect(Collectors.toList());
    }

    public void update(Long id, CompanyRequest companyRequest) {
        Company toBeUpdatedCompany = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        toBeUpdatedCompany.setName(companyRequest.getName());
        companyRepository.save(toBeUpdatedCompany);


    }

    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = CompanyMapper.toEntity(companyRequest);
        return CompanyMapper.toResponse(companyRepository.save(company)) ;
    }

    public List<Employee> findEmployeesByCompanyId(Long id) {
        return employeeRepository.findAllByCompanyId(id);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
