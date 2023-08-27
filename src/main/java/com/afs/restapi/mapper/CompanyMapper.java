package com.afs.restapi.mapper;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.request.CompanyRequest;
import com.afs.restapi.response.CompanyResponse;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class CompanyMapper {
    public static Company toEntity(CompanyRequest companyRequest){
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public static CompanyResponse toResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        List<Employee> companyEmployeesCount = company.getEmployees();
        companyResponse.setEmployeeCount((companyEmployeesCount == null) ? 0 : companyEmployeesCount.size());
        BeanUtils.copyProperties(company,companyResponse);
        return companyResponse;
    }

    public static void updateCompany(Company company, Company updateCompany){
        BeanUtils.copyProperties(company, updateCompany);
    }

}
