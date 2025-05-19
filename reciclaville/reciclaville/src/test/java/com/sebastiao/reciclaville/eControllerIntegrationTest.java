package com.sebastiao.reciclaville;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebastiao.reciclaville.dto.ClienteRequest;
import com.sebastiao.reciclaville.dto.ClienteResponse;
import com.sebastiao.reciclaville.model.Cliente;
import com.sebastiao.reciclaville.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ClienteControllerIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired ClienteRepository repo;

    @BeforeEach
    void cleanup() {
        repo.deleteAll();
    }

    @Test
    void whenPostCliente_thenCreated() throws Exception {
        var req = new ClienteRequest();
        req.setNome("Empresa A");
        req.setCnpj("12345678000199");
        req.setAtividadeEconomica("Comércio");

        var mvc = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Empresa A"))
                .andReturn();

        var resp = objectMapper.readValue(mvc.getResponse().getContentAsString(), ClienteResponse.class);
        assertNotNull(resp.getId());
    }

    @Test
    void whenPostInvalid_thenBadRequest() throws Exception {
        var req = new ClienteRequest(); // sem campos
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGetAllClientes_thenList() throws Exception {
        var c = new Cliente();
        c.setNome("X");
        c.setCnpj("00000000000100");
        c.setAtividadeEconomica("Serviços");
        repo.save(c);

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(c.getId()))
                .andExpect(jsonPath("$[0].nome").value("X"))
                .andExpect(jsonPath("$[0].cnpj").value("00000000000100"));
    }

    @Test
    void whenGetById_thenReturnOne() throws Exception {
        var c = new Cliente();
        c.setNome("Y");
        c.setCnpj("11111111000111");
        c.setAtividadeEconomica("Indústria");
        repo.save(c);

        mockMvc.perform(get("/clientes/{id}", c.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(c.getId()))
                .andExpect(jsonPath("$.nome").value("Y"))
                .andExpect(jsonPath("$.cnpj").value("11111111000111"));
    }

    @Test
    void whenGetNonExisting_thenNotFound() throws Exception {
        mockMvc.perform(get("/clientes/{id}", 9999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPutCliente_thenUpdated() throws Exception {
        var c = new Cliente();
        c.setNome("Z");
        c.setCnpj("22222222000122");
        c.setAtividadeEconomica("Agro");
        repo.save(c);

        var req = new ClienteRequest();
        req.setNome("Z Atualizada");
        req.setCnpj("22222222000122");
        req.setAtividadeEconomica("Agropecuária");

        mockMvc.perform(put("/clientes/{id}", c.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(c.getId()))
                .andExpect(jsonPath("$.nome").value("Z Atualizada"))
                .andExpect(jsonPath("$.atividadeEconomica").value("Agropecuária"));
    }

    @Test
    void whenPutNonExisting_thenNotFound() throws Exception {
        var req = new ClienteRequest();
        req.setNome("X");
        req.setCnpj("33333333000133");
        req.setAtividadeEconomica("Serv");

        mockMvc.perform(put("/clientes/{id}", 8888L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteThenNoContent() throws Exception {
        var c = new Cliente();
        c.setNome("W");
        c.setCnpj("44444444000144");
        c.setAtividadeEconomica("Tech");
        repo.save(c);

        mockMvc.perform(delete("/clientes/{id}", c.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/clientes/{id}", c.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteNonExisting_thenNotFound() throws Exception {
        mockMvc.perform(delete("/clientes/{id}", 7777L))
                .andExpect(status().isNotFound());
    }
}
