package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

class OwnerServiceMapTest {

	OwnerServiceMap ownerServiceMap;
	
	@BeforeEach
	public void setUp() throws Exception {
		ownerServiceMap = new OwnerServiceMap(new PetServiceMap(new PetTypeServiceMap()));
		ownerServiceMap.save(Owner.builder().lastName("Perez").build());
	}

	@Test
	void findAll() {
		Set<Owner> ownerSet = ownerServiceMap.findAll();
		assertEquals(1, ownerSet.size());
	}
	
	@Test
	void findById() {
		Owner owner = ownerServiceMap.findById(1L);
		assertEquals(1L, owner.getId());
	}
	
	@Test
	void save() {
		Owner owner2 = Owner.builder().firstName("Gomez").build();
		ownerServiceMap.save(owner2);
		assertEquals(2, ownerServiceMap.findAll().size());
	}
	
	@Test
	void delete() {
		ownerServiceMap.delete(ownerServiceMap.findById(1L));
		assertEquals(0, ownerServiceMap.findAll().size());
	}
	
	@Test
	void deleteById() {
		ownerServiceMap.deleteById(1l);
		assertEquals(0, ownerServiceMap.findAll().size());
	}
	
	@Test
	void findByLastName() {
		Owner owner = ownerServiceMap.findByLastName("Perez");
		assertNotNull(owner);
	}

}
