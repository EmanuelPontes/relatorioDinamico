package com.empontes.relatoriodinamico.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.empontes.relatoriodinamico.model.AtributoModel;
import com.empontes.relatoriodinamico.model.Relatorio;
import com.empontes.relatoriodinamico.services.RelatorioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/rel/gerar")
public class RelatorioController {
    
    @Autowired
    private RelatorioService relatorioService;

    @PostMapping
    public void gerar(HttpServletResponse response, @RequestBody List<AtributoModel> atributos) throws IOException {
        Relatorio relatorio = new Relatorio(atributos);
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio.pdf");
        this.relatorioService.generateReport(relatorio, response.getOutputStream());
    }
}
