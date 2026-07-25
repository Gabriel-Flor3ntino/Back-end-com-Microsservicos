package br.com.gabrielflorentino.java.back.end.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gabrielflorentino.java.back.end.converter.DTOConverter;
import br.com.gabrielflorentino.java.back.end.dto.ShopDTO;
import br.com.gabrielflorentino.java.back.end.dto.ShopReportDTO;
import br.com.gabrielflorentino.java.back.end.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ShopDTO> getShopsByFilters(Date dataInicio,
                                           Date dataFim,
                                           Float valorMinimo) {

        return reportRepository.getShopByFilters(dataInicio, dataFim, valorMinimo)
                .stream()
                .map(DTOConverter::convert)
                .toList();
    }

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        return reportRepository.getReportByDate(dataInicio, dataFim);
    }
}
