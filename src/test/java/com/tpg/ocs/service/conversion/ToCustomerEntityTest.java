package com.tpg.ocs.service.conversion;

import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.domain.NewCustomerDomainFixture;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentName;
import org.junit.Test;

import static com.tpg.ocs.persistence.entities.CustomerEntityAssertion.assertThat;

public class ToCustomerEntityTest implements NewCustomerDomainFixture, ToCustomerEntity {

    @Test
    public void convertCustomerToEntity() {

        NewCustomer customer = newCustomerJohnDoe();

        CustomerEntity actual = convert(customer);

        PersistentName name = new PersistentName();

        name.setFirstName(customer.getFirstName());
        name.setSurname(customer.getSurname());

        assertThat(actual)
                .hasName(name)
                .hasDateOfBirth(customer.getDateOfBirth())
                .hasAccountNumber(customer.getAccountNumber());
    }
}
