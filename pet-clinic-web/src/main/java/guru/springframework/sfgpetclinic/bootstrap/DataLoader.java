package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final VisitService visitService;
	private final SpecialtyService specialtyService;
	
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			VisitService visitService, SpecialtyService specialtyService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.visitService = visitService;
		this.specialtyService = specialtyService;
	}



	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		if(count == 0) loadData();
	}



	private void loadData() {
		
		PetType dog = PetType.builder()
			.name("Dog")
			.build();
		
		PetType cat = PetType.builder()
			.name("cat")
			.build();
		
		petTypeService.save(cat);
		petTypeService.save(dog);
			
		Specialty radiologySpecialty = Specialty.builder()
			.description("radiology")
			.build();
		
		Specialty surgerySpecialty = Specialty.builder()
			.description("surgery")
			.build();
		
		specialtyService.save(radiologySpecialty);
		specialtyService.save(surgerySpecialty);
		
		System.out.println("Loaded Pet Types and Specialtyes");
		
		Owner owner1 = Owner.builder()
			.firstName("Michael")
			.lastName("Weston")
			.address("123 Brickerel")
			.city("Miami")
			.telephone("123456")
			.build();
		
		Pet mikesDog = Pet.builder()
			.name("Puchi")
			.petType(dog)
			.owner(owner1)
			.birthDate(LocalDate.parse("2021-08-02"))
			.build();
		
		owner1.getPets().add(mikesDog);
		
		ownerService.save(owner1);
		
		Owner owner2 = Owner.builder()
			.firstName("Fiona")
			.lastName("Glenane")
			.address("123 Brickerel")
			.city("Miami")
			.telephone("123456")
			.build();
		
		Pet fionasCat = Pet.builder()
			.name("Misha")
			.petType(cat)
			.owner(owner2)
			.birthDate(LocalDate.parse("2021-12-28"))
			.build();
		
		owner2.getPets().add(fionasCat);
		
		ownerService.save(owner2);
		System.out.println("Loaded owners");
		
		Visit catVisit = Visit.builder()
			.pet(fionasCat)
			.date(LocalDate.now())
			.description("Sneezy Kitty")
			.build();
		
		visitService.save(catVisit);
		
		Vet vet1 = Vet.builder()
			.firstName("Sam")
			.lastName("Axe")
			.specialties(new HashSet<>(Arrays.asList(radiologySpecialty)))
			.build();
		
		Vet vet2 = Vet.builder()
			.firstName("Jessie")
			.lastName("Porter")
			.specialties(new HashSet<>(Arrays.asList(surgerySpecialty)))
			.build();
		
		vetService.save(vet2);
		vetService.save(vet1);
		
		System.out.println("Loaded vets");
	}

}
