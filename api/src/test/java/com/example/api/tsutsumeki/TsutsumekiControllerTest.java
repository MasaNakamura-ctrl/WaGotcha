package com.example.api.tsutsumeki;

import java.awt.PageAttributes;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TsutsumekiController.class)
public class TsutsumekiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TsutsumekiService tsutsumekiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetTsutsumekis() throws Exception{
        Tsutsumeki expected1 = new Tsutsumeki(1, "First");
        Tsutsumeki expected2 = new Tsutsumeki(2,"Second");
        List<Tsutsumeki> tsutsumekiList = Arrays.asList(expected1, expected2);

        when(tsutsumekiService.getAllTsutsumekis()).thenReturn(tsutsumekiList);

        mockMvc.perform(get("/api/tsutsumekis")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].tsutsumeki").value("First"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].tsutsumeki").value("Second"));
    }

    @Test
    void testGetAllTsutsumekis_return500() throws Exception{
        when(tsutsumekiService.getAllTsutsumekis()).thenThrow(new RuntimeException("Internal server error"));
        mockMvc.perform(get("/api/tsutsumekis")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.error").value("Internal server error"));
    }

    @Test
    void testCreateTsutsumeki() throws Exception{
        TsutsumekiRequest request = new TsutsumekiRequest();
        request.setTsutsumeki("posted");
        Tsutsumeki posted = new Tsutsumeki(1, "posted");
        when(tsutsumekiService.postTsutsumeki("posted")).thenReturn(posted);
        mockMvc.perform(post("/api/tsutsumekis")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.tsutsumeki").value("posted"));
    }

    @Test
    void testCreateTsutsumekis_return500() throws Exception{
        TsutsumekiRequest request = new TsutsumekiRequest();
        request.setTsutsumeki("posted");
        when(tsutsumekiService.postTsutsumeki("posted")).thenThrow(new RuntimeException("Internal server error"));
        mockMvc.perform(post("/api/tsutsumekis")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isInternalServerError());
    }

    @Test
    void testUpdateTsutsumeki() throws Exception{
        Tsutsumeki fakeTsutsumeki = new Tsutsumeki();
        String input = "New";
        int id = 1;
        fakeTsutsumeki.setId(id);
        fakeTsutsumeki.setTsutsumeki(input);
        TsutsumekiRequest request = new TsutsumekiRequest();
        request.setTsutsumeki("New");
        when(tsutsumekiService.putTsutsumeki(id,input)).thenReturn(fakeTsutsumeki);
        mockMvc.perform(put("/api/tsutsumekis/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.tsutsumeki").value("New"));
    }

    @Test
    void testUpdateTsutsumeki_notfound() throws Exception{
        int id = 999;
        TsutsumekiRequest request = new TsutsumekiRequest();
        request.setTsutsumeki("error");
        when(tsutsumekiService.putTsutsumeki(id,"error")).thenReturn(null);
        mockMvc.perform(put("/api/tsutsumekis/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void testDeleteTsutsumeki() throws Exception{
        Tsutsumeki fakeTsutsumeki = new Tsutsumeki();
        String input = "Delete";
        int id = 1;
        fakeTsutsumeki.setId(id);
        fakeTsutsumeki.setTsutsumeki(input);
        when(tsutsumekiService.deleteTsutsumeki(id)).thenReturn(fakeTsutsumeki.getId());
        mockMvc.perform(delete("/api/tsutsumekis/{id}", id))
        .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteTsutsumeki_notfound() throws Exception{
        Tsutsumeki fakeTsutsumeki = new Tsutsumeki();
        String input = "Delete";
        int id = 999;
        fakeTsutsumeki.setId(id);
        fakeTsutsumeki.setTsutsumeki(input);
        when(tsutsumekiService.deleteTsutsumeki(id)).thenReturn(0);
        mockMvc.perform(delete("/api/tsutsumekis/{id}", id))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Not Found"));
    }
}
