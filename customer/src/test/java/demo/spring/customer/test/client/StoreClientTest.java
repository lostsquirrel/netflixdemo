package demo.spring.customer.test.client;

import demo.spring.customer.integration.StoreIntegration;
import demo.spring.customer.integration.po.Store;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("junit")
public class StoreClientTest {
    private static final Logger log = LoggerFactory.getLogger(StoreClientTest.class);
    @Autowired
    StoreIntegration storeIntegration;

    @Test
    public void testGetStores() {
        List<Store> stores = storeIntegration.getStores();
        log.info(String.format("get %d stores", stores.size()));
//        Assert.assertTrue(stores.size() == 11);
    }

    @Test
    public void testGetStore() {
        String id = "3a63dc9d300143b39d3120de0865fc0b";
        Store store = storeIntegration.get(id);
        Assert.assertEquals(id, store.getId());
    }
}
