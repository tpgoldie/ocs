package com.tpg.ocs.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static com.tpg.ocs.domain.NewCustomerErrorsAssertion.assertThat;

@RunWith(MockitoJUnitRunner.class)
public abstract class InvalidNewCustomerTest implements NewCustomerDomainFixture {

    @Mock
    protected Errors errors;

    protected NewCustomerValidator validator;

    @Before
    public void setUp() {

        validator = new NewCustomerValidator();
    }
}
