package com.tpg.ocs.util;

import org.apache.commons.lang3.RandomStringUtils;

public interface UniqueIdGeneration {

    default String generateId() {

        return RandomStringUtils.randomAlphanumeric(10);
    }
}
