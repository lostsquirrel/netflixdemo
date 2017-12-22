package demo.spring.customer.integration.fallback;

import demo.spring.customer.integration.StoreIntegration;
import demo.spring.customer.integration.po.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreIntegrationFallback implements StoreIntegration {
    @Override
    public List<Store> getStores() {
        return new ArrayList<>();
    }

    @Override
    public Store update(String storeId, Store store) {
        return new Store("update fallback");
    }

    @Override
    public Store get(String storeId) {
        return new Store("get fallback");
    }
}
