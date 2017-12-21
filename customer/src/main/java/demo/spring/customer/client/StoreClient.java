package demo.spring.customer.client;

import demo.spring.customer.client.po.Store;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "stores") // an arbitrary client name, which is used to create a Ribbon load balancer
public interface StoreClient {
    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") String storeId, Store store);

    @RequestMapping(method = RequestMethod.GET, value = "/stores/{storeId}", consumes = "application/json")
    Store get(@PathVariable("storeId") String storeId);
}
