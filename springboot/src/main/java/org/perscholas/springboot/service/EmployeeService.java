package org.perscholas.springboot.service;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.springboot.database.dao.EmployeeDAO;
import org.perscholas.springboot.database.entity.Employee;
import org.perscholas.springboot.formbean.CreateEmployeeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional(readOnly = true)
    public List<Employee> searchEmployees(String firstNameSearch, String lastNameSearch) {
        log.debug("In the employee search service method: firstName = " + firstNameSearch + " lastName = " + lastNameSearch);

        if (!StringUtils.isEmpty(firstNameSearch) || !StringUtils.isEmpty(lastNameSearch)) {
            return employeeDAO.findByFirstNameContainingAndLastNameContaining(
                    addPercentageIfNeeded(firstNameSearch),
                    addPercentageIfNeeded(lastNameSearch)
            );
        }
        return List.of();
    }

    @Transactional
    public void createEmployee(CreateEmployeeFormBean form) {
        log.info("Form Bean Values - firstName: {}, lastName: {}, departmentName: {}", form.getFirstName(), form.getLastName(), form.getDepartmentName());

        // Check and set default values if necessary
        String firstName = (form.getFirstName() != null) ? form.getFirstName() : "Unknown";
        String lastName = (form.getLastName() != null) ? form.getLastName() : "Unknown";
        String departmentName = (form.getDepartmentName() != null) ? form.getDepartmentName() : "Unknown";

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment(departmentName);

        employeeDAO.save(employee);

        log.info("In create employee service method");
    }

    private String addPercentageIfNeeded(String value) {
        return (!StringUtils.isEmpty(value)) ? "%" + value + "%" : null;
    }
}
