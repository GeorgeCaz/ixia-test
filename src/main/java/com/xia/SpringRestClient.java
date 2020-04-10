package com.xia;

import java.util.*;

import com.xia.model.*;
import com.xia.model.dto.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringRestClient {

	private static final String GET_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/users/{id}";
	private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/users";
	private static final String PERFORM_TRANSFER_ENDPOINT_URL = "http://localhost:8080/api/v1/transfer/{id}";
	private static final String CREATE_USER_ACC_ENDPOINT_URL = "http://localhost:8080/api/v1/createacc/{id}";

	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();

		springRestClient.createUser();

		springRestClient.createUserAcc();

		springRestClient.performTransfer();

		springRestClient.getUserById();
	}

	private void getUserById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		UserDto result = restTemplate.getForObject(GET_USER_ENDPOINT_URL, UserDto.class, params);

		System.out.println(result);
	}

	private void createUser() {
		User newUser = new User("admin", "admin", "admin@gmail.com");
		RestTemplate restTemplate = new RestTemplate();
		User result = restTemplate.postForObject(CREATE_USER_ENDPOINT_URL, newUser, User.class);

		System.out.println(result);
	}


	private void createUserAcc() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setAccType("CURRENT");
		currentAccount.setBalance(1000);

		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setAccType("SAVING");
		savingAccount.setBalance(1000);

		DebitAccount debitAccount = new DebitAccount();
		debitAccount.setAccType("DEBIT");
		debitAccount.setBalance(1000);

		User newUser = new User("admin1", "admin1", "admin@gmail.com1",
				currentAccount, savingAccount, debitAccount);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(CREATE_USER_ACC_ENDPOINT_URL, newUser, params);

	}


	private void performTransfer() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		TransferDto transferDto = new TransferDto();
		transferDto.setSourceAcc("CURRENT");
		transferDto.setAmount(500);
		transferDto.setDestinationAcc("SAVING");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(PERFORM_TRANSFER_ENDPOINT_URL, transferDto, params);


	}

}
