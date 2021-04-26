package guru.springframework.sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet> implements VetService{

	private final SpecialtyService specialtyService;
	
	
	public VetServiceMap(SpecialtyService specialtyService) {
		super();
		this.specialtyService = specialtyService;
	}


	@Override
	public Vet save(Vet vet) {
		if (vet.getSpecialties() != null) {
			vet.getSpecialties().forEach(specialty -> {
				specialtyService.save(specialty);
			});
		}
		return super.save(vet);
	}
	
}
