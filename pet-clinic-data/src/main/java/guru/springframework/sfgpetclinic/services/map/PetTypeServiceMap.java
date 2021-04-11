package guru.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType> implements PetTypeService {

}
