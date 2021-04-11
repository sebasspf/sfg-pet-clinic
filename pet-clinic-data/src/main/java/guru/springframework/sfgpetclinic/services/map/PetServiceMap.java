package guru.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
public class PetServiceMap extends AbstractMapService<Pet> implements PetService{
	
	private PetTypeService petTypeService;
	
	public PetServiceMap(PetTypeService petTypeService) {
		super();
		this.petTypeService = petTypeService;
	}


	@Override
	public Pet save(Pet pet) {
		if (pet.getPetType() != null) {
			petTypeService.save(pet.getPetType());
		} else {
			throw new RuntimeException("Pet Type is required");
		}
		return super.save(pet);
	}
	

}
