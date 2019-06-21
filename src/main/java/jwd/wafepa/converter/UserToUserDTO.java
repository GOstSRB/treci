package jwd.wafepa.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import jwd.wafepa.model.User;
import jwd.wafepa.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO>{
	// konvertujemo sve usere iz originalnog entiteta u entitet UserDTO koji ne sadrzi pasvord i takvu objekat
	// prosledjujemo na net

@Override
	public UserDTO convert (User source) {
		UserDTO dto = new UserDTO();
		dto.setId(source.getId());
		dto.setEmail(source.getEmail());
		dto.setFirstname(source.getFirstname());
		dto.setLastname(source.getLastname());
	
	return dto;
	}
	
// konvertujemo sve usere iz originalnog entiteta u entitet UserDTO koji ne sadrzi pasvord i takvu kolekciju
// prosledjujemo na net
	public List<UserDTO> convert (List<User> source) {
		
		List<UserDTO> retVal = new ArrayList<>();
		
		for(User u: source) {
			UserDTO dto = convert(u);
			retVal.add(dto);
		}
		
		return retVal;
		
	}

}
