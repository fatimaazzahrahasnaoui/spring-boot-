package com.example.demo.persistence.impl;

import com.example.demo.models.Product;
import com.example.demo.persistence.ProductDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {
    private final JdbcTemplate jdbcTemplate;



    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setLabel(rs.getString("label"));
        product.setPrice(rs.getDouble("price"));
        return product;
    };

    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO product (label, price) VALUES (?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, product.getLabel());
            ps.setDouble(2, product.getPrice());
            return ps;
        }, keyHolder);

        // Set the generated ID on the product instance, if available
        if (keyHolder.getKey() != null) {
            product.setId(keyHolder.getKey().longValue());
        }

        // Return 1 to indicate successful insertion (or 0 if key is missing)
        return keyHolder.getKey() != null ? 1 : 0;
    }

    @Override
    public int update(Product product) {
        return jdbcTemplate.update(
                "UPDATE product SET label = ?, price = ? WHERE id = ?",
                product.getLabel(), product.getPrice(), product.getId()
        );
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Product product = jdbcTemplate.queryForObject(
                    "SELECT * FROM product WHERE id = ?",
                    productRowMapper,
                    id
            );
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", productRowMapper);
    }
}
