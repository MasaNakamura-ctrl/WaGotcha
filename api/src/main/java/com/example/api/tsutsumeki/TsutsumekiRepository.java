package com.example.api.tsutsumeki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TsutsumekiRepository {
    private final JdbcTemplate jdbcTemplate;
    public TsutsumekiRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // Read
    public List<Tsutsumeki> findAll(){
        String sql = "SELECT id, tsutsumeki FROM tsutsumekis ORDER BY id DESC";
        return jdbcTemplate.query(sql, new HomeRowMapper());
    }
    private static class HomeRowMapper implements RowMapper<Tsutsumeki>{
        @SuppressWarnings("null")
        @Override
        public Tsutsumeki mapRow(ResultSet rs, int rowNum) throws SQLException{
            Tsutsumeki tsutsumeki = new Tsutsumeki();
            tsutsumeki.setId(rs.getInt("id"));
            tsutsumeki.setTsutsumeki(rs.getString("tsutsumeki"));
            return tsutsumeki;
        }
    }

    // Create
    public int createTsutsumeki(String tsutsumeki){
        String sql = "INSERT INTO tsutsumekis (tsutsumeki) VALUES(?) RETURNING id";
        return jdbcTemplate.queryForObject(sql, Integer.class, tsutsumeki);
    }

    // Update
    public int updateTsutsumeki(int id, String tsutsumeki){
        String sql = "UPDATE tsutsumekis SET tsutsumeki = ? WHERE id = ?";
        return jdbcTemplate.update(sql, tsutsumeki, id);
    }

    // Delete
    public int deleteTsutsumeki(int id){
        String sql = "DELETE FROM tsutsumekis WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
