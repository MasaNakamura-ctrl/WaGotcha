package com.example.api.tsutsumeki;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@ExtendWith(MockitoExtension.class)
public class TsutsumekiRepositoryTest {
    
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    TsutsumekiRepository tsutsumekiRepository;

    @Test
    void testFindAll(){
        Tsutsumeki expected1 = new Tsutsumeki(1, "First");
        Tsutsumeki expected2 = new Tsutsumeki(2,"Second");
        List<Tsutsumeki> tsutsumekiList = Arrays.asList(expected1, expected2);
        String sql = "SELECT id, tsutsumeki FROM tsutsumekis ORDER BY id DESC";
        when(jdbcTemplate.query(
            eq(sql),
            any(RowMapper.class))
        ).thenReturn(tsutsumekiList);
        List<Tsutsumeki> result = tsutsumekiRepository.findAll();
        assertEquals(tsutsumekiList.size(), result.size());
        assertEquals(tsutsumekiList, result);
    }

    @Test
    void testFindTsutsumeki_found(){
        Tsutsumeki expected = new Tsutsumeki(1, "Here I Am!");
        String sql = "SELECT id, tsutsumeki FROM tsutsumekis WHERE id = ?";
        when(jdbcTemplate.queryForObject(
            eq(sql),
            any(RowMapper.class),
            eq(1))
            ).thenReturn(expected);
        Tsutsumeki result = tsutsumekiRepository.findById(1);
        assertNotNull(result);
        assertEquals("Here I Am!", result.getTsutsumeki());
    }

    @Test
    void testFindTsutsumeki_notfound() {
        String sql = "SELECT id, tsutsumeki FROM tsutsumekis WHERE id = ?";
        when(jdbcTemplate.queryForObject(
            eq(sql),
            any(RowMapper.class),
            eq(999))
        ).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));
        Tsutsumeki result = tsutsumekiRepository.findById(999);
        assertEquals(null, result);
    }

    @Test
    void testCreateTsutsumeki(){
        String sql = "INSERT INTO tsutsumekis (tsutsumeki) VALUES(?) RETURNING id";
        when(jdbcTemplate.queryForObject(
        eq(sql),
        eq(Integer.class),
        eq("Created")
        )).thenReturn(1);
        int id = tsutsumekiRepository.createTsutsumeki("Created");
        assertEquals(1, id);
    }

    @Test
    void testUpdateTsutsumeki(){
        String sql = "UPDATE tsutsumekis SET tsutsumeki = ? WHERE id = ?";
        when(jdbcTemplate.update(
        eq(sql),
        eq("New"),
        eq(1)
        )).thenReturn(1);
        int id = tsutsumekiRepository.updateTsutsumeki(1, "New");
        assertEquals(1,id);
    }

    @Test
    void testUpdateTsutsumeki_notfound(){
        String sql = "UPDATE tsutsumekis SET tsutsumeki = ? WHERE id = ?";
        when(jdbcTemplate.update(
            eq(sql),
            eq("New"),
            eq(999))
        ).thenReturn(0);
        int result = tsutsumekiRepository.updateTsutsumeki(999, "New");
        assertEquals(0, result);
    }

    @Test
    void testDeleteTsutsumeki(){
        String sql = "DELETE FROM tsutsumekis WHERE id = ?";
        when(jdbcTemplate.update(
            eq(sql),
            eq(1))
        ).thenReturn(0);
        int result = tsutsumekiRepository.deleteTsutsumeki(1);
        assertEquals(0, result);
    }

    @Test
    void testDeleteTsutsumeki_notfound(){
        String sql = "DELETE FROM tsutsumekis WHERE id = ?";
        when(jdbcTemplate.update(
            eq(sql),
            eq(999))
        ).thenReturn(0);
        int result = tsutsumekiRepository.deleteTsutsumeki(999);
        assertEquals(0, result);
    }
}
