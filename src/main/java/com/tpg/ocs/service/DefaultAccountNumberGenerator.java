package com.tpg.ocs.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultAccountNumberGenerator implements AccountNumberGeneration {

    @Override
    public String generateAccountNumber() {

        return RandomStringUtils.random(10);
    }
}
