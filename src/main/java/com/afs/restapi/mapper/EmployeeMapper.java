package com.afs.restapi.mapper;

import com.afs.restapi.entity.Company;
import com.afs.restapi.request.EmployeeRequest;
import com.afs.restapi.response.EmployeeResponse;
import com.afs.restapi.entity.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return employee;
    }

    public static EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }

    public static void updateEmployee(Employee employee, Employee updateEmployee){
        BeanUtils.copyProperties(employee, updateEmployee);
    }


}
