package jwd.wafepa.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.converter.UserRegistrationDtoToUser;
import jwd.wafepa.converter.UserToUserDTO;
import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.UserDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value="/api/users")
public class ApiUserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserToUserDTO toDTO;
	@Autowired
	private UserRegistrationDtoToUser toUser;
	
//HTTP GET	http://localhost:8080/api/users?name=Bosko
	@RequestMapping(method=RequestMethod.GET, params= {"name"})
	ResponseEntity<List<UserDTO>> getActivities(
			@RequestParam(required=false) String name){
		
		List<User> users = userService.findByName(name);
		
		if(users == null || users.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
	}
	

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUser(){
		List<User> users = userService.findAll();
		
		if(users == null || users.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		User user = userService.findOne(id);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(user),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<User> delete(@PathVariable Long id){
		if(userService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		userService.delete(id);
				
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> add(@RequestBody UserRegistrationDTO newUser){
		
		User converted = toUser.convert(newUser);
		User savedUser = userService.save(converted);
		
		return new ResponseEntity<>(toDTO.convert(savedUser), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<UserDTO> edit(@RequestBody UserRegistrationDTO user, @PathVariable Long id){
		
		if(!id.equals(user.getId())){ 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User converted = toUser.convert(user);	
		User persisted = userService.save(converted);
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	@PostConstruct
	public void init() {
	userService.save(new User(null, "milan1rns@gmail.com", "pas", "Milan", "Radic"));
	userService.save(new User(null, "boskobole@gmail.com", "pas", "Bosko", "Radic"));
	userService.save(new User(null, "alekalek@gmail.com", "pas", "Aleksandra", "Radic"));
	userService.save(new User(null, "lukluk@gmail.com", "pas", "Luka", "Radic"));
	}
	
}
