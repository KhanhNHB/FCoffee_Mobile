package com.group3.fcoffee.presenters;

import com.group3.fcoffee.models.Product;
import com.group3.fcoffee.repositories.FCoffeeRepository;
import com.group3.fcoffee.repositories.FCoffeeRepositoryIMP;
import com.group3.fcoffee.utils.CallBackData;
import com.group3.fcoffee.views.ProductView;

import java.util.List;

public class ProductPresenter {
    private ProductView mProductView;
    private FCoffeeRepository mFCoffeeRepository;

    public ProductPresenter(ProductView mProductView) {
        this.mProductView = mProductView;
        this.mFCoffeeRepository = new FCoffeeRepositoryIMP();
    }

    public void getDrinks(String token) {
        this.mFCoffeeRepository.getProducts(token, new CallBackData<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                mProductView.onProductsSuccess(products);
            }

            @Override
            public void onFail(String message) {
                mProductView.onProductFail(message);
            }
        });
    }
}