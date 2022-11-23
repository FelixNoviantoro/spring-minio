package com.testingproject.minio.dao;

import com.testingproject.minio.dto.CustomerDto;
import com.testingproject.minio.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("namedParameterJdbcTemplateRead")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplateRead;

    public Integer save(CustomerDto.Save data){
        String query = "INSERT INTO public.customers\n" +
                "(firstname, lastname, address, city, imageurl)\n" +
                "VALUES(:firstName, :lastName, :address, :city, :imageUrl)";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("firstName", data.getFirstName());
        map.addValue("lastName", data.getLastName());
        map.addValue("address", data.getAddress());
        map.addValue("city", data.getCity());
        map.addValue("imageUrl", data.getImageUrl());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.namedParameterJdbcTemplate.update(query, map, keyHolder);

        Integer id = (int) keyHolder.getKeys().get("id");

        return id;
    }

    public List<Customer> findAll(){
        String query = "SELECT id, firstname, lastname, address, city, imageurl\n" +
                "FROM public.customers";

        return this.namedParameterJdbcTemplateRead.query(query, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setImageUrl(rs.getString("imageurl"));
                return customer;
            }
        });
    }

    public Optional<Customer> findById(Integer id){
        String query = "SELECT id, firstname, lastname, address, city, imageurl\n" +
                "FROM public.customers where id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource("id", id);

        try {
            return this.namedParameterJdbcTemplateRead.queryForObject(query, map, new RowMapper<Optional<Customer>>() {
                @Override
                public Optional<Customer> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Customer customer = new Customer();
                    customer.setId(id);
                    customer.setFirstName(rs.getString("firstname"));
                    customer.setLastName(rs.getString("lastname"));
                    customer.setAddress(rs.getString("address"));
                    customer.setCity(rs.getString("city"));
                    customer.setImageUrl(rs.getString("imageurl"));
                    return Optional.of(customer);
                }
            });
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }

    }

    public void update(CustomerDto.Update data){
        String query = "UPDATE public.customers\n" +
                "SET address=:address, city=:city\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("address", data.getAddress());
        map.addValue("city", data.getCity());
        map.addValue("id", data.getId());

        this.namedParameterJdbcTemplate.update(query, map);
    }

    public void delete(Integer id){
        String query = "DELETE FROM public.customers\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource("id", id);

        this.namedParameterJdbcTemplate.update(query, map);
    }
}
