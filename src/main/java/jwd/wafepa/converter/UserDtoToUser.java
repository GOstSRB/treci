package jwd.wafepa.converter;

import org.springframework.core.convert.converter.Converter;

import jwd.wafepa.model.User;
import jwd.wafepa.web.dto.UserDTO;

public class UserDtoToUser implements Converter <UserDTO, User>{

	@Override
	public User convert(UserDTO source) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
