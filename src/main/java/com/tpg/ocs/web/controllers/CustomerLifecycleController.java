package com.tpg.ocs.web.controllers;

import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.service.CustomerLifecycleService;
import com.tpg.ocs.web.security.BasicAuthenticationTokens;
import com.tpg.ocs.web.security.HttpHeadersGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/ocs")
public class CustomerLifecycleController implements HttpHeadersGeneration {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerLifecycleController.class);

    private CustomerLifecycleService customerLifecycleService;

    @Autowired
    public CustomerLifecycleController(CustomerLifecycleService customerLifecycleService) {

        this.customerLifecycleService = customerLifecycleService;
    }

    @RequestMapping(value = "/customers",
            method = POST,
            produces = APPLICATION_JSON_UTF8_VALUE)
    @Secured("ROLE_NEW_CUSTOMER")
    public @ResponseBody ResponseEntity<NewCustomer> handleNewCustomerRequest(@RequestBody NewCustomer customer) {

        LOGGER.info("Handling a new customer request ...");

        LOGGER.debug("new customer details: {}", customer);

        Optional<CustomerLifecycleService.Outcome> outcome = customerLifecycleService.save(customer);

        HttpHeaders httpHeaders = generateHttpHeaders();

        LOGGER.info("Request handled.");

        return ok().headers(httpHeaders).body(customer);
    }
}
