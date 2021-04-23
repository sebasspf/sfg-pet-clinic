package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;

public class PetSDJpaService extends AbstractJpaService<Pet, PetRepository> implements PetService{

	public PetSDJpaService(PetRepository repository) {
		super(repository);
	}

}
