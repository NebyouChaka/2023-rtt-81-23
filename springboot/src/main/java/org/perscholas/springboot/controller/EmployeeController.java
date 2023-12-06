package org.perscholas.springboot.controller;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.springboot.database.dao.EmployeeDAO;
import org.perscholas.springboot.database.entity.Employee;
import org.perscholas.springboot.formbean.CreateEmployeeFormBean;
import org.perscholas.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping("/employee/edit/{employeeId}")
    public ModelAndView editEmployee(@PathVariable int employeeId, @RequestParam(required = false) String success) {
        log.info("######################### In /employee/edit #########################");
        ModelAndView response = new ModelAndView("employee/create");

        Employee employee = employeeDAO.findById(employeeId);

        if (!StringUtils.isEmpty(success)) {
            response.addObject("success", success);
        }

        CreateEmployeeFormBean form = new CreateEmployeeFormBean();

        if (employee != null) {
            form.setId(employee.getId());
            form.setFirstName(employee.getFirstName());
            form.setLastName(employee.getLastName());
            form.setDepartmentName(employee.getDepartment());
        } else {
            log.warn("Employee with id " + employeeId + " was not found");
        }

        response.addObject("form", form);

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
    public ModelAndView createEmployeeSubmit(CreateEmployeeFormBean form) {
        ModelAndView response = new ModelAndView("employee/create");

        employeeService.createEmployee(form);

        log.info("In create employee with Args");

        return response;
    }
}
