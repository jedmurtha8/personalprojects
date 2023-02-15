package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JbdcProductDao implements ProductDao{

    private static List<Product> products = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public JbdcProductDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductByNameOrSku(String sku, String name) {
        List<Product> results = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_sku ILIKE ? OR name ILIKE ?";
        SqlRowSet skusAndNames = jdbcTemplate.queryForRowSet(sql, "%" + name + "%", "%" + sku + "%");
        while (skusAndNames.next()) {
            Product product = mapRowToProduct(skusAndNames);
            results.add(product);
        }
        return results;
    }


    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToProduct(results);
        } else {
            return null;
        }
    }

    private Product mapRowToProduct(SqlRowSet results) {
        Product product = new Product();
        product.setId(results.getInt("product_id"));
        product.setSku(results.getString("product_sku"));
        product.setName(results.getString("name"));
        product.setDescription(results.getString("description"));
        product.setPrice(results.getDouble("price"));
        product.setImageName(results.getString("image_name"));
        return product;
    }


}

