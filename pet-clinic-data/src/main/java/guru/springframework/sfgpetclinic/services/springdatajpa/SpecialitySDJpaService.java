package guru.springframework.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService extends AbstractJpaService<Specialty, SpecialtyRepository> implements SpecialtyService{

	public SpecialitySDJpaService(SpecialtyRepository repository) {
		super(repository);
	}

}
