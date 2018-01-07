package com.tpg.ocs.service;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class AccountNumberGenerationAssertion {

    public static AccountNumberGenerationAssertion assertThat(AccountNumberGeneration accountNumberGeneration) {

        return new AccountNumberGenerationAssertion(accountNumberGeneration);
    }

    private final AccountNumberGeneration accountNumberGeneration;

    public AccountNumberGenerationAssertion(AccountNumberGeneration accountNumberGeneration) {

        this.accountNumberGeneration = accountNumberGeneration;
    }

    public AccountNumberGenerationAssertion accountNumberGenerated() {

        verify(accountNumberGeneration).generateAccountNumber();

        return this;
    }

    public AccountNumberGenerationAssertion accountNumberNotGenerated() {

        verify(accountNumberGeneration, never()).generateAccountNumber();

        return this;
    }
}
