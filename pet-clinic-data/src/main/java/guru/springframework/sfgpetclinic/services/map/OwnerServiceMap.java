package guru.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService {
	
	private PetService petService;
	
	
	public OwnerServiceMap(PetService petService) {
		super();
		this.petService = petService;
	}

	@Override
	public Owner findByLastName(String lastName) {
		return null;
	}

	@Override
	public Owner save(Owner owner) {
		if (owner.getPets() != null) {
			owner.getPets().forEach(pet ->{
				petService.save(pet);
			});
		}
		return super.save(owner);
	}
	
	

	

}
