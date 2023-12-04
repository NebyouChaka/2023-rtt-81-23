package org.perscholas.springboot.controller;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.springboot.database.dao.CustomerDAO;
import org.perscholas.springboot.database.entity.Customer;
import org.perscholas.springboot.formbean.CreateCustomerFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class CustomerController {

    // add a label to the existing form input for first name
    // add a 2nd form input for last name
    // change the controller to accept the new form input for last name as well as first name
    // change the query to search by first name OR last name
    // change the query to use like for both first name and last name
    // make both search fields populate the user input if it was given


    @Autowired
    private CustomerDAO customerDao;

    @GetMapping("/customer/edit/{customerId}")
    public ModelAndView editCustomer(@PathVariable int customerId){
        ModelAndView response = new ModelAndView("customer/create");
        Customer customer = customerDao.findById(customerId);

        CreateCustomerFormBean form = new CreateCustomerFormBean();

        if (customer != null) {
            form.setFirstName(customer.getFirstName());
            form.setFirstName(customer.getLastName());
            form.setFirstName(customer.getPhone());
            form.setFirstName(customer.getCity());
        }else {
            log.warn("Customer with id " + customerId + "was not found");
        }
        response.addObject("form", form);
        return response;
    }

    @GetMapping("/customer/search")
    public ModelAndView search(@RequestParam(required = false) String firstNameSearch,
                               @RequestParam(required = false) String lastNameSearch) {
        ModelAndView response = new ModelAndView("customer/search");

        log.debug("in the customer search controller method : first name = " + firstNameSearch + " last name = " + lastNameSearch);

        if (!StringUtils.isEmpty(firstNameSearch) || !StringUtils.isEmpty(lastNameSearch)) {

            response.addObject("firstNameSearch", firstNameSearch);
            response.addObject("lastNameSearch", lastNameSearch);

            if (!StringUtils.isEmpty(firstNameSearch)) {
                firstNameSearch = "%" + firstNameSearch + "%";
            }

            if (!StringUtils.isEmpty(lastNameSearch)) {
                lastNameSearch = "%" + lastNameSearch + "%";
            }

            // we only want to do this code if the user has entered either a first name or a last name
            List<Customer> customers = customerDao.findByFirstNameOrLastName(firstNameSearch, lastNameSearch);

            response.addObject("customerVar", customers);


            for (Customer customer : customers) {
                log.debug("customer: id = " + customer.getId() + " last name = " + customer.getLastName());
            }
        }

        return response;
    }


    @GetMapping("/customer/create")
    public ModelAndView createCustomer() {
        ModelAndView response = new ModelAndView("customer/create");

        log.debug("In create customer with no args - log.debug");
        log.info("In create customer with no args - log.info");

        return response;
    }


    // the action attribute on the form tag is set to /customer/createSubmit so this method will be called when the user clicks the submit button
    @GetMapping("/customer/createSubmit")
    public ModelAndView createCustomerSubmit(CreateCustomerFormBean form) {
        ModelAndView response = new ModelAndView("customer/create");

        log.debug("firstName: " + form.getFirstName());
        log.info("lastName: " + form.getLastName());
        log.info("phone: " + form.getPhone());
        log.info("city: " + form.getCity());

        Customer customer = new Customer();
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setPhone(form.getPhone());
        customer.setCity(form.getCity());

        customerDao.save(customer);

        log.info("In create customer with incoming args");

        return response;
    }


}