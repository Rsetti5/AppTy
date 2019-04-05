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
@RequestMapping("/api")
public class UsersController {

	@Autowired
	private UsersRepository usersRep;
	
	@GetMapping({"/users/get/{user_id}","/merchants/get/{user_id}"})
	public Users getUserById(@PathVariable(value="user_id") Long user_id) throws Exception {
		Users user= usersRep.findById(user_id).orElseThrow(()-> new Exception());
		user.setPassword("");
		return user;
	}
	
	@PostMapping("/users/create")
	public Users createUser(@Valid @RequestBody Users user) {
		user.setPassword(AppTyEncryption.encrypt(user.getPassword()));
		user.setRole("user");
		return usersRep.save(user);
	}
	
	@PostMapping({"/users/validate","/merchants/validate"})
	public Users validateUser(@Valid @RequestBody Users user) {
		user.setPassword(AppTyEncryption.encrypt(user.getPassword()));
		List<Users> list=usersRep.validateUser(user.getUsername(), user.getPassword());
		if(list.size()>0) user= list.get(0);
		user.setPassword("");
		return user;
	}
	
	@PostMapping({"/users/update","/merchants/update"})
	public Users updateUser(@Valid @RequestBody Users user) {
		String password = user.getPassword();
		if(!("".equalsIgnoreCase(password))) {
			user.setPassword(AppTyEncryption.encrypt(password));
		}
		return usersRep.save(user);
	}
	
	
	@PostMapping("/merchants/create")
	public Users createMerchant(@Valid @RequestBody Users user) {
		user.setPassword(AppTyEncryption.encrypt(user.getPassword()));
		user.setRole("merchant");
		user =  usersRep.save(user);		
		return user;
	}
	
}
