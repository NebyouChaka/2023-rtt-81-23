package org.perscholas.springboot.controller;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.springboot.database.dao.EmployeeDAO;
import org.perscholas.springboot.database.entity.Customer;
import org.perscholas.springboot.database.entity.Employee;
import org.perscholas.springboot.formbean.CreateCustomerFormBean;
import org.perscholas.springboot.formbean.CreateEmployeeFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/employee/search")
    public ModelAndView search(@RequestParam(required = false) String firstNameSearch,
                               @RequestParam(required = false) String lastNameSearch) {
        ModelAndView response = new ModelAndView("employee/search");

        log.debug("In the employee search controller method: firstName = " + firstNameSearch + " lastName = " + lastNameSearch);

        if (!StringUtils.isEmpty(firstNameSearch) || !StringUtils.isEmpty(lastNameSearch)) {
            response.addObject("firstNameSearch", firstNameSearch);
            response.addObject("lastNameSearch", lastNameSearch);

            if (!StringUtils.isEmpty(firstNameSearch)) {
                firstNameSearch = "%" + firstNameSearch + "%";
            }

            if (!StringUtils.isEmpty(lastNameSearch)) {
                lastNameSearch = "%" + lastNameSearch + "%";
            }

            List<Employee> employees = employeeDAO.findByFirstNameContainingAndLastNameContaining(firstNameSearch, lastNameSearch);

            response.addObject("employeeVar", employees);

            for (Employee employee : employees) {
                log.debug("Employee: id = " + employee.getId() + " lastName = " + employee.getLastName());
            }
        }

        return response;
    }


    @GetMapping("/employee/create")
    public ModelAndView createEmployee() {
        ModelAndView response = new ModelAndView("employee/create");

        log.debug("In create employee with no args - log.debug");
        log.info("In create employee with no args - log.info");

        return response;
    }

    @GetMapping("/employee/createSubmit")
    public ModelAndView createEmployee(CreateEmployeeFormBean form) {
        ModelAndView response = new ModelAndView("employee/create");

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

        log.info("In create employee with Args");
        return response;
    }


}

