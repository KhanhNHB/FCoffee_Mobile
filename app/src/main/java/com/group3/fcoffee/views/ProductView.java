package com.group3.fcoffee.views;

import com.group3.fcoffee.models.Product;

import java.util.List;

public interface ProductView {
    void onProductsSuccess(List<Product> product);
    void onProductSuccess(Product product);
    void onProductFail(String message);
}
