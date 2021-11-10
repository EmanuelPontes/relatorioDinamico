package com.empontes.relatoriodinamico.services;

import com.empontes.relatoriodinamico.model.Relatorio;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class RelatorioService {
    
    public String generateReport(Relatorio relatorio) {
        String reportPath = "C:/Users/emanuelpontes/OneDrive/Documentos/github/RelatorioDinamico/relatorioDinamico/src/main/resources/reports/Relatorio.jrxml";
        try {
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

            JasperPrint jPrint = JasperFillManager.fillReport(report, relatorio.getParameters());

            JasperExportManager.exportReportToPdfFile(jPrint, "C:/Users/emanuelpontes/OneDrive/Documentos/github/RelatorioDinamico/relatorioDinamico/src/main/resources/reports/Relatorio.pdf");

			System.out.println("Done");

			return "Report successfully generated @path= " + reportPath;
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";

    }
}
