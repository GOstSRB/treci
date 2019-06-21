package jwd.wafepa.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.ActivityDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;
@Component
public class UserRegistrationDtoToUser implements Converter <UserRegistrationDTO, User>{
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(UserRegistrationDTO source) {
		
		User target;
		if (source.getId() == null) {
			target = new User();
		} else {
			target = userService.findOne(source.getId());
					
		}
		
			target.setId(source.getId());
			target.setEmail(source.getEmail());
			target.setPassword(source.getPassword());
			target.setPasswordAgain(source.getPasswordAgain());
			target.setFirstname(source.getFirstname());
			target.setLastname(source.getLastname());
		
			return target;
	
	}
	
	public List<User> convert(List<UserRegistrationDTO> source){
		List<User> target = new ArrayList<>();
		
		for(UserRegistrationDTO dto: source) {
			User u = convert(dto);
			target.add(u);
		}
		
		return target;
	}
	
}
