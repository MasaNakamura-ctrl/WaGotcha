package com.example.api.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class LoginRowMapper implements RowMapper<User>{
        @SuppressWarnings("null")
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException{
            User user = new User();
            user.setId(rs.getInt("userId"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    public User findByName(String userName){
        String sql = "SELECT userId, userName, password FROM users WHERE userName=?;";
        try{
            return jdbcTemplate.queryForObject(sql,  new LoginRowMapper(), userName);
        }catch (Exception e){
            return null;
        }
    }
}
