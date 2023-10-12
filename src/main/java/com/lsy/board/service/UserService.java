package com.lsy.board.service;

import com.lsy.board.model.User;

public interface UserService {
	
	public void register(User user);
	public void update(User user);
	public void delete(Long id);

	public User findByUsername(String username);
	public boolean existsByUsername(String username);

}
