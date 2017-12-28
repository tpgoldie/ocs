package com.tpg.ocs.service.conversion;

import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.domain.CustomerDomainFixture;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentName;
import org.junit.Test;

import static com.tpg.ocs.persistence.entities.CustomerEntityAssertion.assertThat;

public class ToCustomerEntityTest implements CustomerDomainFixture, ToCustomerEntity {

    @Test
    public void convertCustomerToEntity() {

        Customer customer = johnDoe();

        CustomerEntity actual = convert(customer);

        PersistentName name = new PersistentName();

        name.setFirstName(customer.getFirstName());
        name.setSurname(customer.getSurname());

        assertThat(actual)
                .hasName(name)
                .hasAccountNumber(customer.getAccountNumber());
    }
}
