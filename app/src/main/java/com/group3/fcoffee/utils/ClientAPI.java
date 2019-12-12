package com.group3.fcoffee.utils;

import com.group3.fcoffee.services.FCoffeeServices;

public class ClientAPI extends BaseAPI {
    public FCoffeeServices FCoffeeServices() {
        return this.getService(FCoffeeServices.class, ConfigAPI.BASE_URL);
    }
}