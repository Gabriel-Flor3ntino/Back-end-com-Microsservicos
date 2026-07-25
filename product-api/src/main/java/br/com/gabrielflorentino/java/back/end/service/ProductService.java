package br.com.gabrielflorentino.java.back.end.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.gabrielflorentino.java.back.end.dto.ProductDTO;
import br.com.gabrielflorentino.java.back.end.exception.ProductNotFoundException;
import br.com.gabrielflorentino.java.back.end.model.Product;
import br.com.gabrielflorentino.java.back.end.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(ProductDTO::convert).collect(Collectors.toList());
	}

	public List<ProductDTO> getProductByCategoryId(Long categoryId) {

		List<Product> products = productRepository.getProductByCategory(categoryId);
		return products.stream().map(ProductDTO::convert).collect(Collectors.toList());
	}

	public ProductDTO findByProductIdentifier(String productIdentidfier) {
		Product product = productRepository.findByProductIdentifier(productIdentidfier);
		if (product != null) {
			return ProductDTO.convert(product);
		}
		return null;
	}

	public ProductDTO save(ProductDTO productDTO) {
		Product product = productRepository.save(Product.convert(productDTO));
		return ProductDTO.convert(product);
	}

	public ProductDTO delete(long productId) throws ProductNotFoundException{
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
		throw new ProductNotFoundException();
	}
	
	public ProductDTO getProductByIdentifier(String	productIdentifier) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/product/" + productIdentifier;
		ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
		return response.getBody();
	}

}
