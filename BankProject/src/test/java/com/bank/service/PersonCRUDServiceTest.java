package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.bank.exception.BusinessException;
import com.bank.model.Person;
import com.bank.service.impl.PersonCRUDServiceImpl;
import com.bank.service.impl.PersonSearchServiceImpl;

class PersonCRUDServiceTest {


	@Test
	void testCreatePerson() {

		Person person = new Person();
		String firstname = "Bla";
		person.setFirstname(firstname);
		String lastname = "Woods";
		person.setLastname(lastname);
		String password = "abc";
		person.setPassword(password);
		String dob = "2000-03-02";
		person.setDob(dob);
		String email = "bla@example.com";
		person.setEmail(email);
		person.setEmployee(false);
		String phonenumber = "1122334455";
		person.setPhonenumber(phonenumber);

		PersonCRUDService service = new PersonCRUDServiceImpl();
		try {
			int count = service.createPerson(person);
			assertEquals(1, count, "Create Person Failed.");

			PersonSearchService search = new PersonSearchServiceImpl();
			Person personByEmail = search.getPersonByEmail(email);
			assertNotNull(personByEmail, "Could not get Person by email: " + email);

			assertEquals(firstname, personByEmail.getFirstname());
			assertEquals(lastname, personByEmail.getLastname());
			assertEquals(email, personByEmail.getEmail());
			assertEquals(dob, personByEmail.getDob());
			assertEquals(phonenumber, personByEmail.getPhonenumber());

		} catch (BusinessException e) {
			e.printStackTrace();
			fail("ERROR: " + e.getMessage());
		} finally {
			int count;
			try {
				count = service.deletePersonByEmail(email);
				assertEquals(1, count, "DELETE Person Failed.");
			} catch (BusinessException e) {
				e.printStackTrace();
				fail("ERROR: " + e.getMessage());
			}
		}
	}

}
