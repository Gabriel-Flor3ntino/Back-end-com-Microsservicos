package br.com.gabrielflorentino.java.back.end.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.gabrielflorentino.java.back.end.converter.DTOConverter;
import br.com.gabrielflorentino.java.back.end.dto.ItemDTO;
import br.com.gabrielflorentino.java.back.end.dto.ProductDTO;
import br.com.gabrielflorentino.java.back.end.dto.ShopDTO;
import br.com.gabrielflorentino.java.back.end.model.Shop;
import br.com.gabrielflorentino.java.back.end.repository.ShopRepository;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private ProductService productService;
    private UserService userService;
    
    public ShopService(ShopRepository shopRepository, ProductService productService, UserService userService;) {
        this.shopRepository = shopRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public List<ShopDTO> getAll() {
        return convertToDTO(shopRepository.findAll());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return convertToDTO(
                shopRepository.findAllByUserIdentifier(userIdentifier));
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        return convertToDTO(
                shopRepository.findAllByDateGreaterThanEqual(shopDTO.getDate()));
    }

    public ShopDTO findById(long shopId) {
        return shopRepository.findById(shopId)
                .map(ShopDTO::convert)
                .orElse(null);
    }

    public ShopDTO save(ShopDTO shopDTO) {
    	
    	if (userService.getUserByCpf(shopDTO.getUserIdentifier()) == null) {
    		return null;
    	}
    	
    	if (!validateProducts(shopDTO.getItems())) {
    		return null;
    	}
    	
        shopDTO.setTotal(shopDTO.getItems()
                        .stream()
                        .map(x -> x.getPrice())
                        .reduce((float) 0, Float::sum);

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());

        shop = shopRepository.save(shop);
        
        return DTOConverter.convert(shop);
    }

    List<ShopDTO> convertToDTO(List<Shop> shops) {
        return shops.stream()
                .map(ShopDTO::convert)
                .toList();
    }
    

}