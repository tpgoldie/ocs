package com.tpg.ocs.service;

import com.tpg.ocs.domain.NewCustomer;
import lombok.Getter;

import java.util.Optional;

public interface CustomerLifecycleService {

    Optional<Outcome> save(NewCustomer customer);

    @Getter
    public abstract class Outcome {

        private final String message;

        protected Outcome(String message) {

            this.message = message;
        }
    }

    @Getter
    public class NewAccountNumber extends Outcome {

        private final String accountNumber;

        public NewAccountNumber(String value) {

            super("New Customer operation successful");

            this.accountNumber = value;
        }
    }
}
