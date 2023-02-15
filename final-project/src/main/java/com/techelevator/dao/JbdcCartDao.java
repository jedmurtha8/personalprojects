package com.techelevator.dao;

import com.techelevator.model.TaxRateService;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class JbdcCartDao implements CartDao {

    private final JdbcTemplate jdbcTemplate;

    private final TaxRateService taxRateService;

    public JbdcCartDao(JdbcTemplate jdbcTemplate, TaxRateService taxRateService) {
        this.jdbcTemplate = jdbcTemplate;
        this.taxRateService = taxRateService;
    }

    @Override
    public Cart getCart(int userId, String stateCode) {
        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart_item " +
                "JOIN product ON product.product_id = cart_item.product_id " +
                "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while (results.next()) {
            cartItems.add(mapRowToCartItem(results));
        }
        cart.setCartItems(cartItems);

        double subTotal = 0.0;
        for (CartItem cartItem : cartItems) {
            subTotal += cart.getProductTotal() * cartItem.getQuantity();
        }
        cart.setProductTotal(Math.floor(subTotal));

        cart.setTaxRate(taxRateService.getTaxRate(stateCode));

        cart.getAfterTaxTotal((cart.getProductTotal() * cart.getTaxRate()) + cart.getProductTotal());

        return cart;
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, int userId) {
        try {
            String sql = "INSERT INTO cart_item (product_id, quantity, user_id)" +
                    " VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, cartItem.getProductId(), cartItem.getQuantity(), userId);
        } catch (Exception e) {
            String sql = "UPDATE cart_item SET quantity = ? WHERE product_id = ? AND user_id = ?";
            jdbcTemplate.update(sql, cartItem.getQuantity(), cartItem.getProductId(), userId);
        }
    }
    public void deleteItemFromCart(int userId, int productId) {
        String sql = "DELETE FROM cart_item WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, userId, productId);
    }

    public void deleteCart (int userId) {
        String sql = "DELETE FROM cart_item WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }


    private CartItem mapRowToCartItem(SqlRowSet results) {
        CartItem cart = new CartItem();
        cart.setCartItemId(results.getInt("cart_item_id"));
        cart.setUserId(results.getInt("user_id"));
        cart.setQuantity(results.getInt("quantity"));
        cart.setProductId(results.getInt("product_id"));
        return cart;
    }
}
