package br.com.gabrielflorentino.java.back.end.repository;

import java.util.Date;
import java.util.List;

import br.com.gabrielflorentino.java.back.end.dto.ShopReportDTO;
import br.com.gabrielflorentino.java.back.end.model.Shop;

public interface ReportRepository {

	public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);	
	public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);
}
