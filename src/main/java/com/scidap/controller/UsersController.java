package com.scidap.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scidap.model.Users;
import com.scidap.repository.UsersRepository;
import com.scidap.utils.AppTyEncryption;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private UsersRepository usersRep;
	
	@GetMapping("/get/{user_id}")
	public Users getUserById(@PathVariable(value="user_id") Long user_id) throws Exception {
		return usersRep.findById(user_id).orElseThrow(()-> new Exception());
	}
	
	@PostMapping("/create")
	public Users createUser(@Valid @RequestBody Users user) {
		user.setPassword(AppTyEncryption.encrypt(user.getPassword()));
		return usersRep.save(user);
	}
	
	@PostMapping("/validate")
	public boolean validateUser(@Valid @RequestBody Users user) {
		user.setPassword(AppTyEncryption.encrypt(user.getPassword()));
		List<Users> list=usersRep.validateUser(user.getUsername(), user.getPassword());
		if(list.size()>0) return true;
		return false;
	}
}
