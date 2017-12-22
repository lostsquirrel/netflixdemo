package demo.spring.customer.integration.impl;

import demo.spring.customer.integration.StoreIntegration;
import demo.spring.customer.integration.po.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class StoreIntegrationImpl implements StoreIntegration {

    public List<Store> defaultStores(Map<String, Object> parameters) {
        return new ArrayList<Store>();
    }
}
