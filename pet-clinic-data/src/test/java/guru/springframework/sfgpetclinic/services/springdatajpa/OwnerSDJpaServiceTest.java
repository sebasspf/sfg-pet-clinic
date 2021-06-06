package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;



class OwnerSDJpaServiceTest {
	
	OwnerSDJpaService ownerService;
	
	@Mock
	OwnerRepository ownerRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		ownerService = new OwnerSDJpaService(ownerRepository);
	}

	@Test
	void testFindByLastName() {
		Owner returnOwner = Owner.builder().lastName("Perez").build();
		
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		
		Owner owner = ownerService.findByLastName("Perez");
		assertNotNull(owner);
		verify(ownerRepository, times(1)).findByLastName(any());
	}
	
	@Test
	void testFindAll() {
		Owner owner = Owner.builder().lastName("Perez").build();
		Set<Owner> returnedOwners = new HashSet<Owner>();
		returnedOwners.add(owner);
		
		when(ownerRepository.findAll()).thenReturn(returnedOwners);
		
		Set<Owner> owners = ownerService.findAll();
		assertEquals(1, owners.size());
		verify(ownerRepository, times(1)).findAll();
	}

	@Test
	void testFindById() {
		Owner returnedOwner = Owner.builder().lastName("Perez").build();
		Optional<Owner> returnedOptional = Optional.of(returnedOwner);
		when(ownerRepository.findById(anyLong())).thenReturn(returnedOptional);
		
		Owner owner = ownerService.findById(1L);
		assertNotNull(owner);
		verify(ownerRepository, times(1)).findById(eq(1L));
	}

	@Test
	void testSave() {
		Owner returnedOwner = Owner.builder().lastName("Perez").build();
		when(ownerRepository.save(any())).thenReturn(returnedOwner);
		
		Owner savedOwner = ownerService.save(returnedOwner);
		assertNotNull(savedOwner);
		verify(ownerRepository, times(1)).save(eq(returnedOwner));
	}

	@Test
	void testDelete() {
		Owner owner = Owner.builder().lastName("Perez").build();
		
		ownerService.delete(owner);
		verify(ownerRepository, times(1)).delete(eq(owner));
	}

	@Test
	void testDeleteById() {
		ownerService.deleteById(1L);
		verify(ownerRepository, times(1)).deleteById(1l);
	}

}
