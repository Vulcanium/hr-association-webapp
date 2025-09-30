package com.vulcanium.hrassociationv2.repository;

import com.vulcanium.hrassociationv2.configuration.CustomProperties;
import com.vulcanium.hrassociationv2.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Create - Add a new employee
     *
     * @param employee An Employee object
     * @return The Employee object
     */
    public Employee createEmployee(Employee employee) {
        String baseApiUrl = props.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class
        );

        log.debug("Create Employee call {}", response.getStatusCode().toString());

        return response.getBody();
    }


    /**
     * Read - Get an employee by the id
     *
     * @param id The id of the employee
     * @return An Employee object
     */
    public Employee getEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );

        log.debug("Get Employee call {}", response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get all employees
     *
     * @return An iterable of all employees
     */
    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {
                }
        );

        log.debug("Get Employees call {}", response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update - Update an existing employee
     *
     * @param newEmployee The new Employee object replacing the old one
     * @return The Employee object updated
     */
    public Employee updateEmployee(Employee newEmployee) {
        String baseApiUrl = props.getApiUrl();
        String updateEmployeeUrl = baseApiUrl + "/employee/" + newEmployee.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(newEmployee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class
        );

        log.debug("Update Employee call {}", response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Delete - Delete an employee
     *
     * @param id The id of the Employee to delete
     */
    public void deleteEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("Delete Employee call {}", response.getStatusCode().toString());
    }
}
