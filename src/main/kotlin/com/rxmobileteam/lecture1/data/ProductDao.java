package com.rxmobileteam.lecture1.data;

import com.rxmobileteam.lecture1.service.Product;
import com.rxmobileteam.lecture1.service.ProductRespository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * {@link ProductDao} represents a Data Access Object (DAO) for products.
 * The implementation is simplified, so it just uses {@link HashSet} to store.
 * <p>
 * todo: 1. Implement a method {@link ProductDao#add(Product)} that store new product into the set
 * todo: 2. Implement a method {@link ProductDao#findAll()} that returns a set of all products
 */
public class ProductDao implements ProductRespository {
//    private final Set<Product> products = new HashSet<>();
    private final HashMap<Integer, Product> hashMap = new HashMap<>();

    /**
     * Stores a new product
     *
     * @param product a product to store
     * @return {@code true} if a product was stored, {@code false} otherwise
     */
    @Override
    public boolean add(@NotNull Product product) {
        Integer stt = Integer.parseInt(product.getId());
        hashMap.put(stt, product);
        return true;
    }

    /**
     * Returns all stored products
     *
     * @return a set of all stored products
     */
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        hashMap.forEach((key, value) -> products.add(value));
        return products;
    }

}