package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import guru.springframework.sfgpetclinic.repositories.OwnerRepository;

class OwnerSDJpaServiceTest {
	
	OwnerSDJpaService ownerService;
	
	@Mock
	OwnerRepository ownerRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindByLastName() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

}
