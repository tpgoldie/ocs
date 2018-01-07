package com.tpg.ocs.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.ocs.domain.NewCustomerErrorsAssertion.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NewCustomerMissingUsernameTest extends InvalidNewCustomerTest {

    @Test
    public void validateNewCustomerMissingUsername() {

        NewCustomer customer = newCustomerJohnDoe();

        customer.getOcsUser().setUsername("");

        validator.validate(customer, errors);

        assertThat(errors).rejectValueForUsername();
    }
}
