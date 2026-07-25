package br.com.gabrielflorentino.java.back.end.converter;

import br.com.gabrielflorentino.java.back.end.dto.UserDTO;
import br.com.gabrielflorentino.java.back.end.model.User;

public class DTOConverter {
	
	public static UserDTO convert(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setNome(user.getNome());
		userDTO.setEndereco(user.getEndereco());
		userDTO.setCpf(user.getCpf());
		return userDTO;
		}
		
}
