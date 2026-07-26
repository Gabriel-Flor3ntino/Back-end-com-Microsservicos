package br.com.gabrielflorentino.java.back.end.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.gabrielflorentino.java.back.end.dto.ShopReportDTO;
import br.com.gabrielflorentino.java.back.end.model.Shop;

public interface ReportRepository {

	public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);	
	public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);
}
