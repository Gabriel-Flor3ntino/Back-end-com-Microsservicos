package br.com.gabrielflorentino.java.back.end.dto;

import br.com.gabrielflorentino.java.back.end.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ItemDTO {

	@NotBlank
	private String productIdentifier;
	@NotNull
	private Float price;
	
	public String getProductIdentifier() {
		return productIdentifier;
	}
	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public static ItemDTO convert(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductIdentifier(item.getProductIdentifier());
		itemDTO.setPrice(item.getPrice());
		return itemDTO;
	}
	
	
}
