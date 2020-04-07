package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    private static String INSERT = "INSERT INTO TB_CLIENT (NAME) VALUES (?)";
    private static String FIND_ALL = "SELECT * FROM TB_CLIENT";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client){
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public List<Client> findAll(){
        return jdbcTemplate.query(FIND_ALL, (resultSet, i) -> new Client(
                resultSet.getInt("id"),
                resultSet.getString("name")));
    }

}
