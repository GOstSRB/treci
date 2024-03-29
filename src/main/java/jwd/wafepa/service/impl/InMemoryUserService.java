package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class InMemoryUserService implements UserService {

	private Map<Long, User> data = new HashMap<>();
	private Long nextId = 1L;

	@Override
	public User findOne(Long id) {
		return data.get(id);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<>(data.values());
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(nextId);
			nextId += 1;
		}
		data.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(Long id) throws IllegalArgumentException {
		User user = data.remove(id);
		if (user == null) {
			throw new IllegalArgumentException("Removing unexisting user with id=" + id);
		}
	}

	@Override
	public List<User> findByName(String name) {
		List<User> ret = new ArrayList<>();
		
		for (User u : data.values()) {
			if (name.equals(u.getFirstname())) {
				ret.add(u);
			}
		}
		return ret;
	}

}
