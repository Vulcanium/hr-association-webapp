package com.vulcanium.hrassociationv2.service;

import com.vulcanium.hrassociationv2.model.Employee;
import com.vulcanium.hrassociationv2.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final int id) {
        employeeProxy.deleteEmployee(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;

        // The last name should be in upper case
        employee.setLastName(employee.getLastName().toUpperCase());

        if (employee.getId() == null) {
            // If the id is null, then it's a new employee
            savedEmployee = employeeProxy.createEmployee(employee);
        } else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }

        return savedEmployee;
    }

}
