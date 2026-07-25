package br.com.gabrielflorentino.java.back.end.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.gabrielflorentino.java.back.end.converter.DTOConverter;
import br.com.gabrielflorentino.java.back.end.dto.ItemDTO;
import br.com.gabrielflorentino.java.back.end.dto.ProductDTO;
import br.com.gabrielflorentino.java.back.end.dto.ShopDTO;
import br.com.gabrielflorentino.java.back.end.dto.UserDTO;
import br.com.gabrielflorentino.java.back.end.exception.ProductNotFoundException;
import br.com.gabrielflorentino.java.back.end.model.Shop;
import br.com.gabrielflorentino.java.back.end.repository.ShopRepository;

@Service
public class ShopService {

	private final ShopRepository shopRepository;
	private final ProductService productService;
	private final UserService userService;

	public ShopService(ShopRepository shopRepository, ProductService productService, UserService userService) {
		this.shopRepository = shopRepository;
		this.productService = productService;
		this.userService = userService;
	}

	public List<ShopDTO> getAll() {
		List<Shop> shops = shopRepository.findAll();
		return shops.stream().map(DTOConverter::convert).toList();
	}

	public List<ShopDTO> getByUser(String userIdentifier) {
		List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
		return shops.stream().map(DTOConverter::convert).toList();
	}

	public List<ShopDTO> getByDate(ShopDTO shopDTO) {
		List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
		return shops.stream().map(DTOConverter::convert).toList();
	}

	public ShopDTO findById(long ProductId) {
		Optional<Shop> shop = shopRepository.findById(ProductId);
		if (shop.isPresent()) {
			return DTOConverter.convert(shop.get());
		}
		throw new ProductNotFoundException();
	}

	public ShopDTO save(ShopDTO shopDTO, String key) {		
		UserDTO userDTO = userService.getUserByCpf(shopDTO.getUserIdentifier(), key);
		validateProducts(shopDTO.getItems());
		
		shopDTO.setTotal(shopDTO.getItems()
				  .stream()
				  .map(x -> x.getPrice())
				  .reduce((float) 0, Float::sum));
		
		Shop shop = Shop.convert(shopDTO);
		shop.setDate(LocalDateTime.now());
		
		shop = shopRepository.save(shop);
		return DTOConverter.convert(shop);
	}

	private boolean validateProducts(List<ItemDTO> items) {
		for (ItemDTO  item : items) {
			ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
			if (productDTO == null) {
				return false;
			}
			item.setPrice(productDTO.getPreco());
		}
		return true;		
	}

}