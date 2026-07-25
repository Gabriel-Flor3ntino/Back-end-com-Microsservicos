package br.com.gabrielflorentino.java.back.end.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gabrielflorentino.java.back.end.dto.ShopDTO;
import br.com.gabrielflorentino.java.back.end.model.Shop;
import br.com.gabrielflorentino.java.back.end.repository.ShopRepository;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
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
        shopDTO.setTotal(
                (float) shopDTO.getItems()
                        .stream()
                        .mapToDouble(item -> item.getPrice())
                        .sum());

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());

        return ShopDTO.convert(shopRepository.save(shop));
    }

    private List<ShopDTO> convertToDTO(List<Shop> shops) {
        return shops.stream()
                .map(ShopDTO::convert)
                .toList();
    }
}