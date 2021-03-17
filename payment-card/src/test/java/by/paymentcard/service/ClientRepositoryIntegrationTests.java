package by.paymentcard.service;

import by.paymentcard.PaymentCardApplication;
import by.paymentcard.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PaymentCardApplication.class)
public class ClientRepositoryIntegrationTests {

	@Autowired
	ClientRepository repository;

	@Test
	public void findsFirstPageOfCities() {

		List<Client> clients = this.repository.findAll();
		org.hamcrest.MatcherAssert.assertThat(clients.size(), is(greaterThan(1)));
	}
}
