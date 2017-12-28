package integration.com.tpg.ocs.service;

import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.domain.CustomerDomainFixture;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import com.tpg.ocs.service.AccountNumberGeneration;
import com.tpg.ocs.service.CustomerLifecycleService;
import com.tpg.ocs.service.CustomerLifecycleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.tpg.ocs.persistence.repositories.CustomerLifecycleRepositoryAssertion.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SaveNewCustomerTest implements CustomerDomainFixture {

    @TestConfiguration
    static class TestConfig {

        @MockBean
        private AccountNumberGeneration accountNumberGeneration;

        @MockBean
        private CustomerLifecycleRepository customerLifecycleRepository;

        @Bean
        public CustomerLifecycleService customerLifecycleService() {

            return new CustomerLifecycleServiceImpl(customerLifecycleRepository, accountNumberGeneration);
        }
    }

    @Autowired
    private CustomerLifecycleRepository customerLifecycleRepository;

    @Autowired
    private AccountNumberGeneration accountNumberGeneration;

    @Autowired
    private CustomerLifecycleService customerLifecycleService;

    @Test
    public void saveNewCustomer_newCustomer_shouldSaveNewCustomer() {

        Customer customer = johnDoe();

        whenSavingNewCustomer(customer);

        assertThat(customerLifecycleRepository).savesNewCustomer(customer);

        verify(accountNumberGeneration).generateAccountNumber();
    }

    private void whenSavingNewCustomer(Customer customer) {

        when(accountNumberGeneration.generateAccountNumber()).thenReturn(generateId());

        customerLifecycleService.save(customer);
    }
}
