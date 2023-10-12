package com.lsy.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.board.model.User;
import com.lsy.board.repository.UserRepository;

import lombok.extern.java.Log;

@Service
@Log
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}
	
	@Override
	public void update(User user) {
		User user2 = findByUsername(user.getName());
		user2.setEmail(user.getEmail());
		
		// TODO Auto-generated method stub
		userRepository.save(user2);
	}
	
	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findByUsername(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByName(name);
	}
	
	@Override
	public boolean existsByUsername(String name) {
		// TODO Auto-generated method stub
		return userRepository.existsByName(name);
	}
	


}
