package guru.springframework.sfgpetclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@RequestMapping("/owners/{ownerId}")
@Controller
public class PetController {
	
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	private final OwnerService ownerService;
	private final PetService petService;
	private final PetTypeService petTypeService;
	
	public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
		super();
		this.ownerService = ownerService;
		this.petService = petService;
		this.petTypeService = petTypeService;
	}

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes(){
		return petTypeService.findAll();
	}
	
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		Owner owner = ownerService.findById(ownerId);
		return owner;
	}
	
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/pets/new")
	public String initCreationForm(Owner owner, Model model) {
		Pet pet = Pet.builder().build();
		owner.getPets().add(pet);
		pet.setOwner(owner);
		model.addAttribute(pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/pets/new")
	public String processcreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		owner.getPets().add(pet);
		pet.setOwner(owner);
		if (result.hasErrors()) {
			model.addAttribute("pet",pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		} else {
			petService.save(pet);
			return "redirect:/owners/"+owner.getId();
		}
	}
	
	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
		model.addAttribute("pet", petService.findById(petId));
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(@PathVariable("petId") Long petId, @Valid Pet pet, BindingResult result, Owner owner,
			Model model) {
		if (result.hasErrors()) {
			pet.setOwner(owner);
			model.addAttribute("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		} else {
			pet.setId(petId);
			pet.setOwner(owner);
			petService.save(pet);
			return "redirect:/owners/"+owner.getId();
		}
	}
	
	
}
