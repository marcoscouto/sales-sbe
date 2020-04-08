package io.github.marcoscouto.domain.repository;

import io.github.marcoscouto.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    private static String INSERT = "INSERT INTO TB_CLIENT (NAME) VALUES (?)";
    private static String UPDATE = "UPDATE TB_CLIENT SET NAME = ? WHERE ID = ?";
    private static String FIND_ALL = "SELECT * FROM TB_CLIENT";
    private static String FIND_BY_ID = "SELECT * FROM TB_CLIENT WHERE ID = ";
    private static String DELETE = "DELETE FROM TB_CLIENT WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public Client update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[]{client.getName(), client.getId()});
        return client;
    }

    public List<Client> findAll() {
        return jdbcTemplate.query(FIND_ALL, (resultSet, i) -> new Client(
                resultSet.getInt("id"),
                resultSet.getString("name")));
    }

    public List<Client> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID.concat(id.toString()), (resultSet, i) -> new Client(
                resultSet.getInt("id"),
                resultSet.getString("name")));
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }
    public void delete(Client client) {
        jdbcTemplate.update(DELETE, new Object[]{client.getId()});
    }

}
