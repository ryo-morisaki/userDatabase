package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserDatabase;

@Repository
public interface UserDatabase_Repository extends JpaRepository<UserDatabase, Integer> {
	public Optional<UserDatabase> findById(int id);
	public List<UserDatabase> findByName(String key);
	public List<UserDatabase> findByNameLike(String key);
}
