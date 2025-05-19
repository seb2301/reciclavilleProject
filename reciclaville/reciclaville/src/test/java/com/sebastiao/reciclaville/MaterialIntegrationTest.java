package com.sebastiao.reciclaville;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebastiao.reciclaville.dto.MaterialRequest;
import com.sebastiao.reciclaville.dto.MaterialResponse;
import com.sebastiao.reciclaville.model.Material;
import com.sebastiao.reciclaville.repository.MaterialRepository;
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
class MaterialIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired MaterialRepository repo;

    @BeforeEach
    void cleanup() {
        repo.deleteAll();
    }

    @Test
    void whenPostMaterial_thenCreated() throws Exception {
        var req = new MaterialRequest();
        req.setNome("Madeira");
        req.setPercentualCompensacao(12.34);

        var mvc = mockMvc.perform(post("/materiais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Madeira"))
                .andExpect(jsonPath("$.percentualCompensacao").value(12.34))
                .andReturn();

        var resp = objectMapper.readValue(mvc.getResponse().getContentAsString(), MaterialResponse.class);
        assertNotNull(resp.getId());
    }

    @Test
    void whenPostInvalid_thenBadRequest() throws Exception {
        var req = new MaterialRequest();
        mockMvc.perform(post("/materiais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGetAll_thenReturnList() throws Exception {
        var m = repo.save(new Material("Vidro", 5.5));
        mockMvc.perform(get("/materiais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(m.getId()))
                .andExpect(jsonPath("$[0].nome").value("Vidro"))
                .andExpect(jsonPath("$[0].percentualCompensacao").value(5.5));
    }

    @Test
    void whenGetById_thenReturnOne() throws Exception {
        var m = repo.save(new Material("Plástico", 7.7));
        mockMvc.perform(get("/materiais/{id}", m.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(m.getId()))
                .andExpect(jsonPath("$.nome").value("Plástico"))
                .andExpect(jsonPath("$.percentualCompensacao").value(7.7));
    }

    @Test
    void whenGetNonExisting_thenNotFound() throws Exception {
        mockMvc.perform(get("/materiais/{id}", 9999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPut_thenUpdated() throws Exception {
        var m = repo.save(new Material("Metal", 3.3));
        var req = new MaterialRequest();
        req.setNome("Metal Atualizado");
        req.setPercentualCompensacao(8.8);

        mockMvc.perform(put("/materiais/{id}", m.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Metal Atualizado"))
                .andExpect(jsonPath("$.percentualCompensacao").value(8.8));
    }

    @Test
    void whenPutNonExisting_thenNotFound() throws Exception {
        var req = new MaterialRequest();
        req.setNome("X");
        req.setPercentualCompensacao(1.1);

        mockMvc.perform(put("/materiais/{id}", 7777L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDelete_thenNoContent() throws Exception {
        var m = repo.save(new Material("Papel", 4.4));
        mockMvc.perform(delete("/materiais/{id}", m.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/materiais/{id}", m.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteNonExisting_thenNotFound() throws Exception {
        mockMvc.perform(delete("/materiais/{id}", 8888L))
                .andExpect(status().isNotFound());
    }
}
