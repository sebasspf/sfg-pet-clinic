package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.hamcrest.Matchers.hasSize;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.mockito.ArgumentMatchers.anyLong;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

class OwnerControllerTest {

	OwnerController ownerController;
	
	@Mock
	OwnerService ownerService;
	
	Set<Owner> owners;
	
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
	void testListOwners() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/index"))
			.andExpect(model().attribute("owners", hasSize(1)));
		verify(ownerService, times(1)).findAll();
	}

	@Test
	void testFindOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(view().name("notimplemented"));
		
		verifyNoInteractions(ownerService);
	}
	
	@Test
	void testDisplayOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().lastName("Perez").build());
		mockMvc.perform(get("/owners/123"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(model().attributeExists("owner"));
	}

}
