package br.com.gabrielflorentino.java.back.end.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielflorentino.java.back.end.dto.ShopDTO;
import br.com.gabrielflorentino.java.back.end.dto.ShopReportDTO;
import br.com.gabrielflorentino.java.back.end.service.ReportService;
import br.com.gabrielflorentino.java.back.end.service.ShopService;
import jakarta.validation.Valid;

@RestController
public class ShopController {

    private final ShopService shopService;
    private final ReportService reportService;

    public ShopController(ShopService shopService,
                          ReportService reportService) {
        this.shopService = shopService;
        this.reportService = reportService;
    }
	
	@GetMapping("/shopping")
	public List<ShopDTO> getShop() {
		List<ShopDTO> produtos = shopService.getAll();
		return produtos;
	}
	
	@GetMapping("/shopping/shopByUser/{userIdentifier}")
	public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
		List<ShopDTO> produtos = shopService.getByUser(userIdentifier);
		return produtos;
	}
	
	@GetMapping("/shopping/shopByDate")
	public List<ShopDTO> getShops (@RequestBody ShopDTO shopDTO) {
		List<ShopDTO> produtos = shopService.getByDate(shopDTO);
		return produtos;
	}
	
	@GetMapping("/shopping/{id}")
	public ShopDTO findById(@PathVariable Long id) {
		return shopService.findById(id);
	}
	
	@PostMapping("/shopping")
	public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO) {
		return shopService.save(shopDTO);
	}
	
	@GetMapping("/shopping/search")
	public List<ShopDTO> getShopsByFilter(
										@RequestParam(name = "dataInicio", required = true)
										@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
										@RequestParam(name = "dataFim", required=false)
										@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
										@RequestParam(name = "valorMinimo", required=false)
										Float valorMinimo) {
		return reportService.getShopsByFilters(dataInicio, dataFim, valorMinimo);
	}
	
	@GetMapping("/shopping/report")
	public ShopReportDTO getReportByDate(
									@RequestParam(name = "dataInicio", required=true)
									@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
									@RequestParam(name = "dataFim", required=true)
									@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
		return reportService.getReportByDate(dataInicio, dataFim);
	}
}
