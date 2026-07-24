package br.com.gabrielflorentino.java.back.end.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielflorentino.java.back.end.dto.UserDTO;
import br.com.gabrielflorentino.java.back.end.service.UserService;
import jakarta.annotation.PostConstruct;

@RestController
public class UserController {

	public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<UserDTO> getUsers() {
		List<UserDTO> usuarios = userService.getAll();
		return usuarios;
	}

	@GetMapping("/users/{id}")
	public UserDTO findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping("/user")
	public UserDTO newUser(@RequestBody UserDTO userDTO) {
		return userService.save(userDTO);
	}

	@GetMapping("/user/cpf/{cpf}")
	public UserDTO findByCpf(@PathVariable String cpf) {
		return userService.findByCpf(cpf);

	}

	@DeleteMapping("/user/{id}")
	public UserDTO delete(@PathVariable Long id) {
		return userService.delete(id);
	}

	@GetMapping("/user/search")
	public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true) String name) {
		return userService.queryByName(name);
	}
}