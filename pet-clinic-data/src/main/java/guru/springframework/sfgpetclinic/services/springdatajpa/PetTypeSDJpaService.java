package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;

public class PetTypeSDJpaService extends AbstractJpaService<PetType, PetTypeRepository> implements PetTypeService{

	public PetTypeSDJpaService(PetTypeRepository repository) {
		super(repository);
	}

}
