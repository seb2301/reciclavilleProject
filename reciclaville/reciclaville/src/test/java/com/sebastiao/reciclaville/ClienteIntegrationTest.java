package com.sebastiao.reciclaville;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebastiao.reciclaville.dto.ClienteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ClienteIntegrationTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    private Long existenteId;

    @BeforeEach
    void setup() throws Exception {
        String json = mapper.writeValueAsString(
                new ClienteRequest("ACME","12345678000100","Indústria","João")
        );
        var res = mvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        existenteId = mapper.readTree(res.getResponse().getContentAsString())
                .get("id").asLong();
    }

    @Test
    void whenGetAll_then200() throws Exception {
        mvc.perform(get("/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].nome").isNotEmpty());
    }

    @Test
    void whenGetById_then200() throws Exception {
        mvc.perform(get("/clientes/{id}", existenteId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existenteId))
                .andExpect(jsonPath("$.cnpj").isNotEmpty());
    }

    @Test
    void whenGetNotFound_then404() throws Exception {
        mvc.perform(get("/clientes/{id}", 99999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPostValid_then201() throws Exception {
        var req = new ClienteRequest("Nova","00000000000191","Serviços","Maria");
        mvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Nova"));
    }

    @Test
    void whenPostInvalid_then400() throws Exception {
        // todos em branco → violação de @NotBlank
        mvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPutValid_then200() throws Exception {
        var update = new ClienteRequest("Alterado","11111111000191","Comércio","Ana");
        mvc.perform(put("/clientes/{id}", existenteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Alterado"));
    }

    @Test
    void whenPutNotFound_then404() throws Exception {
        var update = new ClienteRequest("X","Y","Z","W");
        mvc.perform(put("/clientes/{id}", 88888L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteValid_then204() throws Exception {
        mvc.perform(delete("/clientes/{id}", existenteId))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDeleteNotFound_then404() throws Exception {
        mvc.perform(delete("/clientes/{id}", 77777L))
                .andExpect(status().isNotFound());
    }
}
