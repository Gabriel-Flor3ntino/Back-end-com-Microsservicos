package br.com.gabrielflorentino.java.back.end.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.gabrielflorentino.java.back.end.dto.ProductDTO;
import br.com.gabrielflorentino.java.back.end.exception.UserNotFoundException;

public class ProductService {

	public ProductDTO getProductByIdentifier(String productIdentifier) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8081/product/" + productIdentifier;
			ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e){
			throw new UserNotFoundException();
		}
	}
}
