package guru.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService {

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
