package guru.springframework.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService extends AbstractJpaService<Owner, OwnerRepository> implements OwnerService{

	public OwnerSDJpaService(OwnerRepository repository) {
		super(repository);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return repository.findByLastName(lastName);
	}

	
	
}
