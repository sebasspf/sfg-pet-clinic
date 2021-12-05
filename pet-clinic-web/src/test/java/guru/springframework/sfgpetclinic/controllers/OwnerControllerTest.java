package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

class OwnerControllerTest {

	OwnerController ownerController;
	
	@Mock
	OwnerService ownerService;
	
	Set<Owner> owners;
	List<Owner> ownersList;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		ownerController = new OwnerController(ownerService);
		owners = new HashSet<Owner>();
		owners.add(Owner.builder().lastName("Perez").build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}
	
	@Test
	void testDisplayOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().lastName("Perez").build());
		mockMvc.perform(get("/owners/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void processCreationForm() throws Exception{
		Owner owner1 = new Owner();
		owner1.setId(1l);
		
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner1);
		
		mockMvc.perform(post("/owners/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"))
			.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any());
	}
	
	@Test
	void initUpdateOwnerForm() throws Exception {
		Owner owner = Owner.builder().build();
		owner.setId(1L);
		
		when(ownerService.findById(anyLong())).thenReturn(owner);
		
		mockMvc.perform(get("/owners/1/edit"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void processUpdateOwnerForm() throws Exception {
		Owner owner = Owner.builder().build();
		owner.setId(1L);
		
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner);
		
		mockMvc.perform(post("/owners/1/edit"))
			.andExpect(view().name("redirect:/owners/1"))
			.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any());
	}
	
	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/findOwners"))
			.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void processFindFormReturnMany() throws Exception {
		Owner owner = new Owner();
		owner.setId(1l);
		Owner owner2 = new Owner();
		owner2.setId(2l);
		List<Owner> owners = new ArrayList<Owner>(Arrays.asList(owner,owner2));
		
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);
		mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownersList"))
			.andExpect(model().attribute("selections", hasSize(2)));
	}
	
	@Test
	void processFindReturnOne() throws Exception {
		List<Owner> owners = new ArrayList<Owner>();
		Owner owner = Owner.builder().lastName("Perez").build();
		owner.setId(1l);
		owners.add(owner);
		
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);
		
		mockMvc.perform(get("/owners"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"));
	}

}
