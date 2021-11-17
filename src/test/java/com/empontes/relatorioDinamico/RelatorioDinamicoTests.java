package com.empontes.relatoriodinamico;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Date;
import com.empontes.relatoriodinamico.model.entity.Atributo;

public class RelatorioDinamicoTests {
    

    private static final String API_ROOT = "http://localhost:8080/rel/attrbts";

    @Test
    public void whenAll_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }


    @Test 
    public void whenAddNew_thenAccept() {
        final Atributo atributo = this.createRandom();

        final Response response = RestAssured.given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(atributo)
        .post(API_ROOT);

        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatusCode());

    }

    @Test 
    public void whenDelete_thenOk() {
        String location = this.getUriOfCreated(this.createRandom(2));

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }


    private Atributo createRandom() {
        final Atributo atributo = new Atributo();
        atributo.setName(randomAlphabetic(10));
        atributo.setDescription(randomAlphabetic(30));
        atributo.setType(1);
        return atributo;
    }  
    
    private Atributo createRandom(Integer type) {
        final Atributo atributo = new Atributo();
        atributo.setName(randomAlphabetic(10));
        atributo.setDescription(randomAlphabetic(30));
        atributo.setType(type);
        return atributo;
    }  
    private String getUriOfCreated(Atributo atributo) {
        final Response response = RestAssured.given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(atributo)
        .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath()
            .get("id");
    }
}
