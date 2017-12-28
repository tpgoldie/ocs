package com.tpg.ocs.web.controllers;

import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.service.CustomerLifecycleService;
import com.tpg.ocs.web.security.BasicAuthenticationTokens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/shop")
public class CustomerLifecycleController implements BasicAuthenticationTokens {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerLifecycleController.class);

    private CustomerLifecycleService customerLifecycleService;

    @Autowired
    public CustomerLifecycleController(CustomerLifecycleService customerLifecycleService) {

        this.customerLifecycleService = customerLifecycleService;
    }

    @PostMapping(value = "/customers", consumes = {APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_JSON_UTF8_VALUE}, produces = APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Customer> handleNewCustomerRequest(Customer customer) {

        LOGGER.info("Handling a new customer request ...");

        Optional<CustomerLifecycleService.Outcome> outcome = customerLifecycleService.save(customer);

        MultiValueMap<String, String> httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, createBasicAuthentication());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return new HttpEntity<>(customer, httpHeaders);
    }
}
