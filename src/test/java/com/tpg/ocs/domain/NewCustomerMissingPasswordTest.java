package com.tpg.ocs.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.ocs.domain.NewCustomerErrorsAssertion.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NewCustomerMissingPasswordTest extends InvalidNewCustomerTest {

    @Test
    public void validateNewCustomerMissingPassword() {

        NewCustomer customer = newCustomerJohnDoe();

        customer.getOcsUser().setPassword("");

        validator.validate(customer, errors);

        assertThat(errors).rejectValueForPassword();
    }
}
