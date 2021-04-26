package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

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
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final VisitService visitService;
	
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, VisitService visitService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.visitService = visitService;
	}



	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		if(count == 0) loadData();
	}



	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		
		petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
	
		petTypeService.save(cat);
		
		System.out.println("Loaded Pet Types");
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("123456");
		
		Pet mikesDog = new Pet();
		mikesDog.setName("Puchi");
		mikesDog.setPetType(dog);
		mikesDog.setOwner(owner1);
		mikesDog.setBirthDate(LocalDate.parse("2021-08-02"));
		
		owner1.getPets().add(mikesDog);
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenane");
		owner2.setAddress("123 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("123456");
		
		Pet fionasCat = new Pet();
		fionasCat.setName("Misha");
		fionasCat.setPetType(cat);
		fionasCat.setOwner(owner2);
		fionasCat.setBirthDate(LocalDate.parse("2021-12-28"));
		
		owner2.getPets().add(fionasCat);
		
		ownerService.save(owner2);
		
		Visit catVisit = new Visit();
		catVisit.setPet(fionasCat);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Sneezy Kitty");
		
		visitService.save(catVisit);
		
		System.out.println("Loaded owners");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		Specialty radiologySpecialty = new Specialty();
		radiologySpecialty.setDescription("radiology");
		
		vet1.getSpecialties().add(radiologySpecialty);
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		
		Specialty surgerySpecialty = new Specialty();
		surgerySpecialty.setDescription("surgery");
		
		vet2.getSpecialties().add(surgerySpecialty);
		
		vetService.save(vet2);
		
		System.out.println("Loaded vets");
	}

}
