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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class RelatorioService {
    
    public String generateReport(Relatorio relatorio, OutputStream outputStream) {
        
        String reportPath = "src/main/resources/reports/Relatorio.jrxml";
        
        try {

            File file = new File(reportPath);
            InputStream inputStream = new FileInputStream(file);
            JasperReport report = JasperCompileManager.compileReport(inputStream);
            JasperPrint jPrint = JasperFillManager.fillReport(report, relatorio.getParameters(), new JREmptyDataSource());

            JasperExportManager.exportReportToPdfFile(jPrint, "src/main/resources/reports/Relatorio.pdf");
            
            this.exportXlsxToFile(jPrint, new File("src/main/resources/reports/Relatorio.xlsx"));

            JasperExportManager.exportReportToPdfStream(jPrint, outputStream);
			
            System.out.println("Done");

			return "Report successfully generated @path= " + reportPath;
        } catch (JRException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";

    }

    private void exportXlsxToOutputStream(JasperPrint jPrint, OutputStream outputStream) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        configuration.setDetectCellType(true);//Set configuration as you like it!!
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    private void exportXlsxToFile(JasperPrint jPrint, File file) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        configuration.setWhitePageBackground(false);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setCellHidden(false);
        configuration.setDetectCellType(true);//Set configuration as you like it!!
        configuration.setCollapseRowSpan(false);
        configuration.setIgnoreCellBorder(true);
        configuration.setIgnoreGraphics(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
}
