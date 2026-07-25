package br.com.gabrielflorentino.java.back.end.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.gabrielflorentino.java.back.end.converter.DTOConverter;
import br.com.gabrielflorentino.java.back.end.dto.UserDTO;
import br.com.gabrielflorentino.java.back.end.exception.UserNotFoundException;
import br.com.gabrielflorentino.java.back.end.model.User;
import br.com.gabrielflorentino.java.back.end.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        return convertToDTO(userRepository.findAll());
    }

    public UserDTO findById(long userId) {
        return userRepository.findById(userId)
        		.map(DTOConverter::convert)
                .orElse(null);
    }

    public UserDTO save(UserDTO dto) {
        return DTOConverter.convert(
                userRepository.save(User.convert(dto))
        );
    }

    public void delete(long userId) {
        userRepository.findById(userId)
                .ifPresent(userRepository::delete);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
        	return DTOConverter.convert(user);
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByName(String name) {
        return convertToDTO(userRepository.queryByNameLike(name));
    }

    private List<UserDTO> convertToDTO(List<User> users) {
        return users.stream()
                .map(DTOConverter::convert)
                .toList();
    }
    
    public UserDTO getUserByCpf(String cpf) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String url = "http://localhost:8080/user/cpf/" + cpf;
    	ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
    	return response.getBody();
    }
}
