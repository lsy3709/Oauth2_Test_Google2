package com.lsy.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsy.board.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByName(String name);
	public boolean existsByName(String name);
	Optional<User> findByEmail(String email); 
}
