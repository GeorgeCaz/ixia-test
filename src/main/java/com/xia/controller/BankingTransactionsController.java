package com.xia.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.xia.exception.ResourceNotFoundException;
import com.xia.model.TransferDto;
import com.xia.model.User;
import com.xia.model.dto.UserDto;
import com.xia.service.ManageAccount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xia.repository.UserRepository;


@RestController
@RequestMapping("/api/v1")
public class BankingTransactionsController {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ManageAccount manageAccount;


	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUserId(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		UserDto userDto = manageAccount.findById(userId);

		return ResponseEntity.ok().body(userDto);
	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping("/transfer/{id}")
	public ResponseEntity<?> performTransfer(@PathVariable(value = "id") Long userId,
											@RequestBody TransferDto dto) throws ResourceNotFoundException {
		User user  = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		manageAccount.transferMoneyBetweenAcc(dto.getSourceAcc(), dto.getDestinationAcc(), dto.getAmount(), userId);

		return ResponseEntity.ok("Transfer executed with success");
	}

	@PutMapping("/createacc/{id}")
	public ResponseEntity<UserDto> createUserAcc(@PathVariable(value = "id") Long userId,
											 @RequestBody User userWithAcc) throws ResourceNotFoundException {
		User user1 = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		user1.setFirstName(userWithAcc.getFirstName());
		user1.setCurrentAccount(userWithAcc.getCurrentAccount());
		user1.setSavingAccount(userWithAcc.getSavingAccount());
		user1.setDebitAccount(userWithAcc.getDebitAccount());

		User userWithAccCreated = userRepository.save(user1);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userWithAccCreated, UserDto.class);
		return ResponseEntity.ok().body(userDto);
	}
}
