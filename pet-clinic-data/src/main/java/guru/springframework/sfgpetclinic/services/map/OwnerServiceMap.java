package guru.springframework.sfgpetclinic.services.map;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService {
	
	private PetService petService;
	
	
	public OwnerServiceMap(PetService petService) {
		super();
		this.petService = petService;
	}

	@Override
	public Owner findByLastName(String lastName) {
		return this.findAll()
				.stream()
				.filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
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

	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		// TODO impl
		return null;
	}
	
	

	

}
