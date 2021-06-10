package com.springboot.restservices.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	// GET /users
	// retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// GET /users/{id}
	// retrieveOneUser
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		
		if (user==null) {
			throw new UserNotFoundException("user id "+id+" not found");
		}
		
		//"all-users", SERVER_PATH + "/users"
		// retrieveAllUsers

		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(link.withRel("all-users"));
		
		return resource;
	}

	// POST /users
	// createUser
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		//CREATED
		// /users/{id}		savedUser.getId()
		java.net.URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}

	// DELETE /users/{id}
	// deleteUser
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.delete(id);
		
		if (user==null) {
			throw new UserNotFoundException("user id "+id+" not found");
		}
	}
}
