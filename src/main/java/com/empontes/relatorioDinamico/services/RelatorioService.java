package com.empontes.relatoriodinamico.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import com.empontes.relatoriodinamico.model.Relatorio;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class RelatorioService {
    
    public String generateReport(Relatorio relatorio, OutputStream outputStream) {
       // String reportPath = "C:/Users/emanuelpontes/OneDrive/Documentos/github/RelatorioDinamico/relatorioDinamico/src/main/resources/reports/Relatorio.jasper";
        String reportPath = "C:\\Users\\Emanuel Pontes SIS\\OneDrive\\Documentos\\github\\RelatorioDinamico\\relatorioDinamico\\src\\main\\resources\\reports\\Relatorio.jrxml";
        // String reportPath = "../../resources/reports/Relatorio.jrxml";
        try {

            File file = new File(reportPath);
            InputStream inputStream = new FileInputStream(file);
            // JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport report = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(report, relatorio.getParameters(), new JREmptyDataSource());

            JasperExportManager.exportReportToPdfFile(jPrint, "src/main/resources/reports/Relatorio.pdf");
            
            JasperExportManager.exportReportToPdfStream(jPrint, outputStream);
			
            System.out.println("Done");

			return "Report successfully generated @path= " + reportPath;
        } catch (JRException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";

    }
}
