package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.service.AccountNumberGeneration;

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
}
