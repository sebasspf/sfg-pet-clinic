package guru.springframework.sfgpetclinic.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Controller
public class VisitController {
	
	private final VisitService visitService;
	private final PetService petService;
	
	public VisitController(VisitService visitService, PetService petService) {
		super();
		this.visitService = visitService;
		this.petService = petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model) {
		Pet pet = petService.findById(petId);
		model.addAttribute("pet", pet);
		Visit visit = new Visit();
		pet.getVisits().add(visit);
		return visit;
	}
	
	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("petId") Long petId, Model model) {
		return "pets/createOrUpdateVisitForm";
	}
	
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@Valid Visit visit, BindingResult result, @PathVariable("petId") Long petId) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		} else {
			Pet pet = petService.findById(petId);
			visit.setPet(pet);
			visitService.save(visit);
			return "redirect:/owners/{ownerId}";
		}
	}
	
}
