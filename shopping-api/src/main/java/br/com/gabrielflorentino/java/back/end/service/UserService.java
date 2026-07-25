package br.com.gabrielflorentino.java.back.end.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.gabrielflorentino.java.back.end.dto.UserDTO;
import br.com.gabrielflorentino.java.back.end.exception.UserNotFoundException;

public class UserService {

	public UserDTO getUserByCpf(String cpf, String cfp) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/user/cpf/" + cpf;
			ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new UserNotFoundException();
		}
	}
}
