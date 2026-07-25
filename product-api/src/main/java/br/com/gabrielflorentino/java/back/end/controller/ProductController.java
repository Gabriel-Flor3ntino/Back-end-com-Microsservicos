package br.com.gabrielflorentino.java.back.end.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielflorentino.java.back.end.dto.ProductDTO;
import br.com.gabrielflorentino.java.back.end.exception.ProductNotFoundException;
import br.com.gabrielflorentino.java.back.end.service.ProductService;
import jakarta.validation.Valid;

@RequestMapping("/product")
@RestController
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<ProductDTO> getProducts() {
		return productService.getAll();
	}

	@GetMapping("/category/{categoryId}")
	public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId) {
		return productService.getProductByCategoryId(categoryId);
	}

	@GetMapping("/{productIdentifier}")
	public ProductDTO findById(@PathVariable String productIdentifier) {
		return productService.findByProductIdentifier(productIdentifier);
	}

	@PostMapping
	public ProductDTO newProduct(@Valid @RequestBody ProductDTO productDTO) {
		return productService.save(productDTO);
	}

	@DeleteMapping("/{id}")
	public ProductDTO delete(@PathVariable Long id) throws ProductNotFoundException {
	    return productService.delete(id);
	}
}
