package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.UserDatabase_Interface;
import com.example.demo.data.UserDatabase_Service;
import com.example.demo.model.UserDatabase;


@RestController
public class UserDatabase_Controller {
	@Autowired
	UserDatabase_Service service;
	
	@GetMapping("/UserDatabase")
	@CrossOrigin
	public List<? extends UserDatabase_Interface> UserDatabase() {
		System.out.println("UserDatabase/index");
		return service.getAll();
	}
	
	@GetMapping("/UserDatabase/{id}")
	@CrossOrigin
	public UserDatabase_Interface UserById(@PathVariable int id) {
		System.out.println("/UserDatabase/findById");
		return service.getById(id);
	}
	
	@PostMapping("/UserDatabase/add")
	@CrossOrigin
	public int add(@RequestBody UserDatabase userDatabase) {
		System.out.println("UserDatabase/add(post)");
		return service.add(userDatabase);
	}
	
	@PostMapping("/UserDatabase/update")
	@CrossOrigin
	public int update(@RequestBody UserDatabase userDatabase) {
		System.out.println("apartment/update(post)");
		return service.add(userDatabase);
	}
	
	@PostMapping("/UserDatabase/delete")
	@CrossOrigin
	public void delete(@RequestBody UserDatabase userDatabase) {
		System.out.println("UserDatabase/delete(post)");
		service.delete(userDatabase);
	}
	
	
	@PostMapping("/UserDatabase/find")
	@CrossOrigin
	public List<? extends UserDatabase_Interface> find(@RequestParam("find") String find) {
		System.out.println("UserDatabase/find(post)");
		return service.findByNameLike(find);
	}
}
