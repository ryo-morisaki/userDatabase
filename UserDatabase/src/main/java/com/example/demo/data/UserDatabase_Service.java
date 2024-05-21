package com.example.demo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserDatabase;
import com.example.demo.repositories.UserDatabase_Repository;

@Service
public class UserDatabase_Service {
	@Autowired
	private UserDatabase_Repository repository;
	
	public List<? extends UserDatabase_Interface> getAll() {
		System.out.println("Service getAll");
		return repository.findAll();
	}
	
	public UserDatabase_Interface getById(int id) {
		System.out.println("Servise:getById id[" + "]");
		return repository.findById(id).orElse(null);
	}
	
	public List<? extends UserDatabase_Interface>findByNameLike(String find) {
		System.out.println("Service:getByNameLike find[" + find + "]");
		return repository.findByNameLike("%" + find + "%");
	}
	
	// 情報の追加
		public int add(UserDatabase_Interface item) {
			System.out.println("Service:add [" + item + "]");
			if (item instanceof UserDatabase) {
				UserDatabase savedItem = repository.saveAndFlush((UserDatabase)item);
				return savedItem.getUser_id();
			}
			return 0;
		}
		
		// 情報の削除
		public void delete(UserDatabase_Interface item) {
			System.out.println("Service:delete[" + item + "]");
			if (item instanceof UserDatabase) {
				repository.delete((UserDatabase) item);
			}
		}
}
