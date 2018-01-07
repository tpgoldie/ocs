package com.tpg.ocs.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ValidateNewCustomerTest implements NewCustomerDomainFixture {

    @Mock
    private Errors errors;

    private NewCustomerValidator validator;

    @Before
    public void setUp() {

        validator = new NewCustomerValidator();
    }

    @Test
    public void supportsClass() {

        assertTrue(validator.supports(NewCustomer.class));
    }

    @Test
    public void doesNotSupportsClass() {

        assertFalse(validator.supports(Customer.class));
    }

    @Test
    public void validateNewCustomer() {

        NewCustomer customer = newCustomerJohnDoe();

        validator.validate(customer, errors);

        verify(errors, never()).reject("username");

        verify(errors, never()).reject("password");
    }
}
