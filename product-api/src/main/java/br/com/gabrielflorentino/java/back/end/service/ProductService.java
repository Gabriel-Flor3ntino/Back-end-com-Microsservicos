package br.com.gabrielflorentino.java.back.end.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.gabrielflorentino.java.back.end.converter.DTOConverter;
import br.com.gabrielflorentino.java.back.end.dto.ProductDTO;
import br.com.gabrielflorentino.java.back.end.exception.CategoryNotFoundException;
import br.com.gabrielflorentino.java.back.end.exception.ProductNotFoundException;
import br.com.gabrielflorentino.java.back.end.model.Product;
import br.com.gabrielflorentino.java.back.end.repository.CategoryRepository;
import br.com.gabrielflorentino.java.back.end.repository.ProductRepository;

@Service
public class ProductService {

	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<ProductDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(DTOConverter::convert).toList();
	}

	public List<ProductDTO> getProductByCategoryId(Long categoryId) {
		List<Product> products = productRepository.getProductByCategory(categoryId);
		return products.stream().map(DTOConverter::convert).toList();
	}

	public ProductDTO findByProductIdentifier(String productIdentidfier) {
		Product product = productRepository.findByProductIdentifier(productIdentidfier);
		if (product != null) {
			return DTOConverter.convert(product);
		}
		throw new ProductNotFoundException();
	}

	public ProductDTO save(ProductDTO productDTO) {
		Boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
		if (!existsCategory) {
			throw new CategoryNotFoundException();
		}
		
		Product product = productRepository.save(Product.convert(productDTO));
		return DTOConverter.convert(product);
	}

	public ProductDTO delete(long id) throws ProductNotFoundException{
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
		throw new ProductNotFoundException();
	}
	
}
